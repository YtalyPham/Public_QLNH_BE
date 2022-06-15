package com.example.backend_qlnh.converter;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.dto.DichVuDTO.CreateDichVuDTO;
import com.example.backend_qlnh.dto.DichVuDTO.ViewDichVuDTO;
import com.example.backend_qlnh.entity.HinhAnh;
import com.example.backend_qlnh.entity.DichVu;
import com.example.backend_qlnh.exception.ConvertEntityDTOException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.repository.DichVuRepository;
import com.example.backend_qlnh.repository.HinhAnhRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DichVuConverter {
    @Autowired
    DichVuRepository dichVuRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    HinhAnhRepository hinhAnhRepository;

    public ViewDichVuDTO convertToDTO(DichVu dichVu){
        ViewDichVuDTO dto= modelMapper.map(dichVu,ViewDichVuDTO.class);
        if(dichVu.getHinhAnhDV()==null){
            dto.setMaHinhAnh(null);
        }else{
            Long maHA= dichVu.getHinhAnhDV().getMaHinhAnh();
            dto.setMaHinhAnh(maHA);
        }

        return dto;
    }

    public DichVu convertToEntity(CreateDichVuDTO createDichVuDTO) throws ConvertEntityDTOException {
        try{
            DichVu dichVu= modelMapper.map(createDichVuDTO,DichVu.class);

            if(createDichVuDTO.getMaHinhAnh()==null){
                dichVu.setHinhAnhDV(null);
            }else {
                Optional<HinhAnh> opt= hinhAnhRepository.findById(createDichVuDTO.getMaHinhAnh());
                HinhAnh hinhAnh;
                if(!opt.isPresent()){
                    throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
                }
                hinhAnh= opt.get();
                dichVu.setHinhAnhDV(hinhAnh);
            }

            return dichVu;

        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

    public List<ViewDichVuDTO> convertToListDTO(List<DichVu> listEntity) throws ConvertEntityDTOException {
        List<ViewDichVuDTO> listDTO;
        try{
            listDTO=listEntity.stream().map(this::convertToDTO).collect(Collectors.toList());
        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
        return listDTO;
    }





}
