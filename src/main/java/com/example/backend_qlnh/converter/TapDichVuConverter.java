package com.example.backend_qlnh.converter;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.dto.TapDichVuDTO.CreateTapDichVuDTO;
import com.example.backend_qlnh.entity.DichVu;
import com.example.backend_qlnh.entity.TapDichVu;
import com.example.backend_qlnh.exception.ConvertEntityDTOException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.repository.DichVuRepository;
import com.example.backend_qlnh.repository.TapDichVuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TapDichVuConverter {
    @Autowired
    TapDichVuRepository tapDichVuRepository;
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    DichVuRepository dichVuRepository;

    public CreateTapDichVuDTO convertToDTO(TapDichVu tapDichVu){
        CreateTapDichVuDTO dto= modelMapper.map(tapDichVu,CreateTapDichVuDTO.class);
        dto.setMaDichVu(tapDichVu.getDichVuTDV().getMaDichVu());
        return dto;
    }

    public TapDichVu convertToEntity(CreateTapDichVuDTO createtapDichVuDTO) throws ConvertEntityDTOException {
        try{
            TapDichVu tapDichVu= modelMapper.map(createtapDichVuDTO,TapDichVu.class);


            Optional<DichVu> opt1= dichVuRepository.findById(createtapDichVuDTO.getMaDichVu());
            DichVu dichVu;
            if(!opt1.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_DICHVU_NOT_FOUND);
            }
            dichVu= opt1.get();
            tapDichVu.setDichVuTDV(dichVu);
            return tapDichVu;

        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

    public List<CreateTapDichVuDTO> convertToListDTO(List<TapDichVu> listEntity) throws ConvertEntityDTOException {
        List<CreateTapDichVuDTO> listDTO;
        try{
            listDTO=listEntity.stream().map(this::convertToDTO).collect(Collectors.toList());
        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
        return listDTO;
    }
}
