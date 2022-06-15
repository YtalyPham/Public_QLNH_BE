package com.example.backend_qlnh.converter;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.dto.SanhTiecDTO.CreateSanhTiecDTO;

import com.example.backend_qlnh.dto.SanhTiecDTO.ViewSanhTiecDTO;
import com.example.backend_qlnh.entity.HinhAnh;
import com.example.backend_qlnh.entity.SanhTiec;
import com.example.backend_qlnh.exception.ConvertEntityDTOException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.repository.HinhAnhRepository;
import com.example.backend_qlnh.repository.SanhTiecRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SanhTiecConverter {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    SanhTiecRepository sanhTiecRepository;
    @Autowired
    HinhAnhRepository hinhAnhRepository;

    public ViewSanhTiecDTO convertToDTO(SanhTiec sanhTiec){
        ViewSanhTiecDTO dto= modelMapper.map(sanhTiec,ViewSanhTiecDTO.class);
        Long maHA= sanhTiec.getHinhAnhST().getMaHinhAnh();
        dto.setMaHinhAnh(maHA);
        return dto;
    }

    public SanhTiec convertToEntity(CreateSanhTiecDTO createSanhTiecDTO) throws ConvertEntityDTOException {
        try{
            SanhTiec sanhTiec= modelMapper.map(createSanhTiecDTO,SanhTiec.class);

            Optional<HinhAnh> opt= hinhAnhRepository.findById(createSanhTiecDTO.getMaHinhAnh());
            HinhAnh hinhAnh;
            if(!opt.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
            }
            hinhAnh= opt.get();
            sanhTiec.setHinhAnhST(hinhAnh);
            return sanhTiec;

        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

    public List<ViewSanhTiecDTO> convertToListDTO(List<SanhTiec> listEntity) throws ConvertEntityDTOException {
        List<ViewSanhTiecDTO> listDTO;
        try{
            listDTO=listEntity.stream().map(this::convertToDTO).collect(Collectors.toList());
        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
        return listDTO;
    }




}
