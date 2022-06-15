package com.example.backend_qlnh.converter;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.dto.NguyenLieuDTO.CreateNguyenLieuDTO;
import com.example.backend_qlnh.dto.NguyenLieuDTO.ViewDetailNguyenLieuDTO;
import com.example.backend_qlnh.entity.NguyenLieu;
import com.example.backend_qlnh.exception.ConvertEntityDTOException;
import com.example.backend_qlnh.repository.NguyenLieuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import java.util.stream.Collectors;

@Component
public class NguyenLieuConverter {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    NguyenLieuRepository nguyenLieuRepository;

    public ViewDetailNguyenLieuDTO convertToDTO(NguyenLieu nguyenLieu){
        ViewDetailNguyenLieuDTO dto= modelMapper.map(nguyenLieu,ViewDetailNguyenLieuDTO.class);
        return dto;
    }

    public NguyenLieu convertToEntity(CreateNguyenLieuDTO createNguyenLieuDTO) throws ConvertEntityDTOException {
        try{
            NguyenLieu nguyenLieu= modelMapper.map(createNguyenLieuDTO,NguyenLieu.class);
            return nguyenLieu;

        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

    public List<ViewDetailNguyenLieuDTO> convertToListDTO(List<NguyenLieu> listEntity) throws ConvertEntityDTOException {
        List<ViewDetailNguyenLieuDTO> listDTO;
        try{
            listDTO=listEntity.stream().map(this::convertToDTO).collect(Collectors.toList());
        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
        return listDTO;
    }
    
    
    
    
}
