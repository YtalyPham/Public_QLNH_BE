package com.example.backend_qlnh.converter;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.dto.LoaiNhanVienDTO.ViewDetailLoaiNhanVien;
import com.example.backend_qlnh.dto.LoaiNhanVienDTO.CreateLoaiNhanVienDTO;
import com.example.backend_qlnh.entity.LoaiNhanVien;
import com.example.backend_qlnh.exception.ConvertEntityDTOException;
import com.example.backend_qlnh.repository.LoaiNhanVienRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoaiNhanVienConverter {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    LoaiNhanVienRepository loaiNhanVienRepository;

    public ViewDetailLoaiNhanVien convertToDTO(LoaiNhanVien loaiNhanVien){
        ViewDetailLoaiNhanVien dto= modelMapper.map(loaiNhanVien,ViewDetailLoaiNhanVien.class);
        return dto;
    }

    public LoaiNhanVien convertToEntity(CreateLoaiNhanVienDTO createLoaiNhanVienDTO) throws ConvertEntityDTOException {
        try{
            LoaiNhanVien loaiNhanVien= modelMapper.map(createLoaiNhanVienDTO,LoaiNhanVien.class);
            return loaiNhanVien;

        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

    public List<ViewDetailLoaiNhanVien> convertToListDTO(List<LoaiNhanVien> listEntity) throws ConvertEntityDTOException {
        List<ViewDetailLoaiNhanVien> listDTO;
        try{
            listDTO=listEntity.stream().map(this::convertToDTO).collect(Collectors.toList());
        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
        return listDTO;
    }
}
