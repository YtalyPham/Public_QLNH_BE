package com.example.backend_qlnh.converter;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.dto.MonAnDTO.CreateMonAnDTO;
import com.example.backend_qlnh.dto.MonAnDTO.ViewDetailMonAnDTO;
import com.example.backend_qlnh.dto.ThucDonDTO.CreateThucDonDTO;
import com.example.backend_qlnh.dto.ThucDonDTO.ViewThucDonDTO;
import com.example.backend_qlnh.entity.HinhAnh;
import com.example.backend_qlnh.entity.MonAn;
import com.example.backend_qlnh.entity.NguyenLieu;
import com.example.backend_qlnh.entity.ThucDon;
import com.example.backend_qlnh.exception.ConvertEntityDTOException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.repository.HinhAnhRepository;
import com.example.backend_qlnh.repository.MonAnRepository;
import com.example.backend_qlnh.repository.NguyenLieuRepository;
import com.example.backend_qlnh.repository.ThucDonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ThucDonConverter {
    @Autowired
    ThucDonRepository thucDonRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    HinhAnhRepository hinhAnhRepository;
    @Autowired
    MonAnRepository monAnRepository;

    public ViewThucDonDTO convertToDTO(ThucDon thucDon){
        ViewThucDonDTO dto= modelMapper.map(thucDon,ViewThucDonDTO.class);
        dto.setMaHinhAnh(thucDon.getHinhAnhTD().getMaHinhAnh());
        dto.setMaMonAn(thucDon.getMonAnTD().getMaMonAn());
        return dto;
    }

    public ThucDon convertToEntity(CreateThucDonDTO createThucDonDTO) throws ConvertEntityDTOException {
        try{
            ThucDon thucDon= modelMapper.map(createThucDonDTO,ThucDon.class);
            Optional<HinhAnh> opt= hinhAnhRepository.findById(createThucDonDTO.getMaHinhAnh());
            HinhAnh hinhAnh;
            if(!opt.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
            }
            hinhAnh= opt.get();
            thucDon.setHinhAnhTD(hinhAnh);

            Optional<MonAn> opt1= monAnRepository.findById(createThucDonDTO.getMaMonAn());
            MonAn monAn;
            if(!opt1.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_MONAN_NOT_FOUND);
            }
            monAn= opt1.get();
            thucDon.setMonAnTD(monAn);
            return thucDon;

        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

    public List<ViewThucDonDTO> convertToListDTO(List<ThucDon> listEntity) throws ConvertEntityDTOException {
        List<ViewThucDonDTO> listDTO;
        try{
            listDTO=listEntity.stream().map(this::convertToDTO).collect(Collectors.toList());
        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
        return listDTO;
    }

}
