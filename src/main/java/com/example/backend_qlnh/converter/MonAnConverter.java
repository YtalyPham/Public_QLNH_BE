package com.example.backend_qlnh.converter;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.dto.MonAnDTO.CreateMonAnDTO;

import com.example.backend_qlnh.dto.MonAnDTO.ViewDetailMonAnDTO;
import com.example.backend_qlnh.entity.HinhAnh;
import com.example.backend_qlnh.entity.MonAn;
import com.example.backend_qlnh.entity.NguyenLieu;
import com.example.backend_qlnh.entity.NhaHang;
import com.example.backend_qlnh.exception.ConvertEntityDTOException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.repository.HinhAnhRepository;
import com.example.backend_qlnh.repository.MonAnRepository;
import com.example.backend_qlnh.repository.NguyenLieuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MonAnConverter {
    @Autowired
    MonAnRepository monAnRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    HinhAnhRepository hinhAnhRepository;
    @Autowired
    NguyenLieuRepository nguyenLieuRepository;

    public ViewDetailMonAnDTO convertToDTO(MonAn monAn){
        ViewDetailMonAnDTO dto= modelMapper.map(monAn,ViewDetailMonAnDTO.class);
        dto.setMaHinhAnh(monAn.getHinhAnhMA().getMaHinhAnh());
        dto.setMaNguyenLieu(monAn.getNguyenLieuMA().getMaNguyenLieu());
        return dto;
    }

    public MonAn convertToEntity(CreateMonAnDTO createMonAnDTO) throws ConvertEntityDTOException {
        try{
            MonAn monAn= modelMapper.map(createMonAnDTO,MonAn.class);

            Optional<HinhAnh> opt= hinhAnhRepository.findById(createMonAnDTO.getMaHinhAnh());
            HinhAnh hinhAnh;
            if(!opt.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
            }
            hinhAnh= opt.get();
            monAn.setHinhAnhMA(hinhAnh);

            Optional<NguyenLieu> opt1= nguyenLieuRepository.findById(createMonAnDTO.getMaNguyenLieu());
            NguyenLieu nguyenLieu;
            if(!opt1.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_NGUYENLIEU_NOT_FOUND);
            }
            nguyenLieu= opt1.get();
            monAn.setNguyenLieuMA(nguyenLieu);
            return monAn;

        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

    public List<ViewDetailMonAnDTO> convertToListDTO(List<MonAn> listEntity) throws ConvertEntityDTOException {
        List<ViewDetailMonAnDTO> listDTO;
        try{
            listDTO=listEntity.stream().map(this::convertToDTO).collect(Collectors.toList());
        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
        return listDTO;
    }
    
    
    
}
