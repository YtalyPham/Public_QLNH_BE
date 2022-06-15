package com.example.backend_qlnh.converter;

import com.example.backend_qlnh.constants.ErrorCode;

import com.example.backend_qlnh.dto.LoaiHinhSuKienDTO.CreateLoaiHinhSuKienDTO;
import com.example.backend_qlnh.dto.LoaiHinhSuKienDTO.ViewLoaiHinhSuKienDTO;
import com.example.backend_qlnh.entity.LoaiHinhSuKien;

import com.example.backend_qlnh.exception.ConvertEntityDTOException;
import com.example.backend_qlnh.repository.LoaiHinhSuKienRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoaiHinhSuKienConverter {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    LoaiHinhSuKienRepository loaiHinhSuKienRepository;

    public ViewLoaiHinhSuKienDTO convertToDTO(LoaiHinhSuKien loaiHinhSuKien){
        ViewLoaiHinhSuKienDTO dto= modelMapper.map(loaiHinhSuKien,ViewLoaiHinhSuKienDTO.class);
        return dto;
    }

    public LoaiHinhSuKien convertToEntity(CreateLoaiHinhSuKienDTO createLoaiHinhSuKienDTO) throws ConvertEntityDTOException {
        try{
            LoaiHinhSuKien LoaiHinhSuKien= modelMapper.map(createLoaiHinhSuKienDTO,LoaiHinhSuKien.class);
            return LoaiHinhSuKien;

        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

    public List<ViewLoaiHinhSuKienDTO> convertToListDTO(List<LoaiHinhSuKien> listEntity) throws ConvertEntityDTOException {
        List<ViewLoaiHinhSuKienDTO> listDTO;
        try{
            listDTO=listEntity.stream().map(this::convertToDTO).collect(Collectors.toList());
        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
        return listDTO;
    }
}
