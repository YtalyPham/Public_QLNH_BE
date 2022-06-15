package com.example.backend_qlnh.converter;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.dto.HinhAnhDTO.CreateHinhAnhDTO;
import com.example.backend_qlnh.dto.HinhAnhDTO.ViewDetailHinhAnhDTO;
import com.example.backend_qlnh.entity.HinhAnh;
import com.example.backend_qlnh.exception.ConvertEntityDTOException;
import com.example.backend_qlnh.repository.HinhAnhRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HinhAnhConverter {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    HinhAnhRepository hinhAnhRepository;

    public ViewDetailHinhAnhDTO convertToDTO(HinhAnh hinhAnh){
        ViewDetailHinhAnhDTO viewDetailHinhAnhDTO= modelMapper.map(hinhAnh,ViewDetailHinhAnhDTO.class);
        return  viewDetailHinhAnhDTO;
    }

    public HinhAnh convertToEntity(CreateHinhAnhDTO createHinhAnhDTO){
        HinhAnh hinhAnh= modelMapper.map(createHinhAnhDTO,HinhAnh.class);
        return hinhAnh;
    }

    public List<ViewDetailHinhAnhDTO> convertToListDTO(List<HinhAnh> listEntity) throws ConvertEntityDTOException{
        List<ViewDetailHinhAnhDTO> listDTO;
        try{
            listDTO=listEntity.stream().map(this::convertToDTO).collect(Collectors.toList());
        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
        return listDTO;
    }






}
