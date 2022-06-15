package com.example.backend_qlnh.converter;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.dto.HinhAnhDTO.CreateHinhAnhDTO;
import com.example.backend_qlnh.dto.HinhAnhDTO.ViewDetailHinhAnhDTO;
import com.example.backend_qlnh.dto.NhaHangDTO.CreateNhaHangDTO;
import com.example.backend_qlnh.dto.NhaHangDTO.ViewNhaHangDTO;
import com.example.backend_qlnh.entity.HinhAnh;
import com.example.backend_qlnh.entity.NhaHang;
import com.example.backend_qlnh.exception.ConvertEntityDTOException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.repository.HinhAnhRepository;
import com.example.backend_qlnh.repository.NhaHangRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class NhaHangConverter {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    NhaHangRepository nhaHangRepository;

    @Autowired
    HinhAnhRepository hinhAnhRepository;

    public ViewNhaHangDTO convertToDTO(NhaHang nhaHang){
        ViewNhaHangDTO dto= modelMapper.map(nhaHang,ViewNhaHangDTO.class);
        Long maNhaHang= nhaHang.getHinhAnhNH().getMaHinhAnh();
        dto.setMaHinhAnh(maNhaHang);
        return dto;
    }

    public NhaHang convertToEntity(CreateNhaHangDTO createNhaHangDTO) throws ConvertEntityDTOException{
        try{
            NhaHang nhaHang= modelMapper.map(createNhaHangDTO,NhaHang.class);
            Optional<HinhAnh> opt= hinhAnhRepository.findById(createNhaHangDTO.getMaHinhAnh());
            HinhAnh hinhAnh;
            if(!opt.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
            }
            hinhAnh= opt.get();
            nhaHang.setHinhAnhNH(hinhAnh);
            return nhaHang;

        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

    public List<ViewNhaHangDTO> convertToListDTO(List<NhaHang> listEntity) throws ConvertEntityDTOException {
        List<ViewNhaHangDTO> listDTO;
        try{
            listDTO=listEntity.stream().map(this::convertToDTO).collect(Collectors.toList());
        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
        return listDTO;
    }


}
