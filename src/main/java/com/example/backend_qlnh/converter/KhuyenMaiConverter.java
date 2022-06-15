package com.example.backend_qlnh.converter;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.dto.KhuyenMaiDTO.CreateKhuyenMaiDTO;
import com.example.backend_qlnh.dto.KhuyenMaiDTO.ViewDetailKhuyenMaiDTO;

import com.example.backend_qlnh.entity.HinhAnh;

import com.example.backend_qlnh.entity.KhuyenMai;
import com.example.backend_qlnh.exception.ConvertEntityDTOException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.repository.HinhAnhRepository;
import com.example.backend_qlnh.repository.KhuyenMaiRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class KhuyenMaiConverter {
    @Autowired
    KhuyenMaiRepository khuyenMaiRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    HinhAnhRepository hinhAnhRepository;



    public ViewDetailKhuyenMaiDTO convertToDTO(KhuyenMai KhuyenMai){
        ViewDetailKhuyenMaiDTO dto= modelMapper.map(KhuyenMai,ViewDetailKhuyenMaiDTO.class);
        Long maHA= KhuyenMai.getHinhAnhKM().getMaHinhAnh();

        dto.setMaHinhAnh(maHA);

        return dto;
    }

    public KhuyenMai convertToEntity(CreateKhuyenMaiDTO createKhuyenMaiDTO) throws ConvertEntityDTOException {
        try{
            KhuyenMai khuyenMai= modelMapper.map(createKhuyenMaiDTO,KhuyenMai.class);

            Optional<HinhAnh> opt= hinhAnhRepository.findById(createKhuyenMaiDTO.getMaHinhAnh());
            HinhAnh hinhAnh;
            if(!opt.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
            }
            hinhAnh= opt.get();
            khuyenMai.setHinhAnhKM(hinhAnh);


            return khuyenMai;

        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

    public List<ViewDetailKhuyenMaiDTO> convertToListDTO(List<KhuyenMai> listEntity) throws ConvertEntityDTOException {
        List<ViewDetailKhuyenMaiDTO> listDTO;
        try{
            listDTO=listEntity.stream().map(this::convertToDTO).collect(Collectors.toList());
        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
        return listDTO;
    }
}
