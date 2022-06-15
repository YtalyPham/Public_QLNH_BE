package com.example.backend_qlnh.converter;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.dto.DanhGiaDTO.CreateDanhGiaDTO;
import com.example.backend_qlnh.dto.DanhGiaDTO.ViewDanhGiaDTO;
import com.example.backend_qlnh.dto.NhaHangDTO.CreateNhaHangDTO;
import com.example.backend_qlnh.dto.NhaHangDTO.ViewNhaHangDTO;
import com.example.backend_qlnh.entity.DanhGia;
import com.example.backend_qlnh.entity.HinhAnh;
import com.example.backend_qlnh.entity.KhachHang;
import com.example.backend_qlnh.entity.NhaHang;
import com.example.backend_qlnh.exception.ConvertEntityDTOException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.repository.DanhGiaRepository;
import com.example.backend_qlnh.repository.KhachHangRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DanhGiaConverter {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    DanhGiaRepository danhGiaRepository;
    @Autowired
    KhachHangRepository khachHangRepository;

    public ViewDanhGiaDTO convertToDTO(DanhGia danhGia){
        ViewDanhGiaDTO dto= modelMapper.map(danhGia,ViewDanhGiaDTO.class);
        Long maKhachHang= danhGia.getKhachHangDG().getMaKhachHang();
        dto.setMaKhachHang(maKhachHang);
        return dto;
    }

    public DanhGia convertToEntity(CreateDanhGiaDTO createDanhGiaDTO) throws ConvertEntityDTOException {
        try{
            DanhGia danhGia= modelMapper.map(createDanhGiaDTO,DanhGia.class);
            Optional<KhachHang> opt= khachHangRepository.findById(createDanhGiaDTO.getMaKhachHang());
            KhachHang khachHang;
            if(!opt.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            }
            khachHang= opt.get();
            danhGia.setKhachHangDG(khachHang);
            return danhGia;

        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

    public List<ViewDanhGiaDTO> convertToListDTO(List<DanhGia> listEntity) throws ConvertEntityDTOException {
        List<ViewDanhGiaDTO> listDTO;
        try{
            listDTO=listEntity.stream().map(this::convertToDTO).collect(Collectors.toList());
        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
        return listDTO;
    }
    
    
}
