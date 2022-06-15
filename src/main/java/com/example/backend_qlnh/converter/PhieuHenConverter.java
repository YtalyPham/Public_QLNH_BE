package com.example.backend_qlnh.converter;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.dto.KhachHangDTO.CreateKhachHangDTO;
import com.example.backend_qlnh.dto.KhachHangDTO.ViewKhachHangDTO;
import com.example.backend_qlnh.dto.PhieuHenDTO.CreatePhieuHenDTO;
import com.example.backend_qlnh.dto.PhieuHenDTO.ViewPhieuHenDTO;
import com.example.backend_qlnh.entity.*;
import com.example.backend_qlnh.exception.ConvertEntityDTOException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.repository.KhachHangRepository;
import com.example.backend_qlnh.repository.LoaiHinhSuKienRepository;
import com.example.backend_qlnh.repository.PhieuHenRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PhieuHenConverter {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PhieuHenRepository phieuHenRepository;
    @Autowired
    KhachHangRepository khachHangRepository;
    @Autowired
    LoaiHinhSuKienRepository loaiHinhSuKienRepository;

    public ViewPhieuHenDTO convertToDTO(PhieuHen phieuHen){
        ViewPhieuHenDTO dto= modelMapper.map(phieuHen,ViewPhieuHenDTO.class);
        Long maKH= phieuHen.getKhachHangPH().getMaKhachHang();
        Long maLHSK= phieuHen.getLoaiHinhSuKienPH().getMaLoaiHinhSuKien();
        dto.setMaKhachHang(maKH);
        dto.setMaLoaiHinhSuKien(maLHSK);
        return dto;
    }

    public PhieuHen convertToEntity(CreatePhieuHenDTO createPhieuHenDTO) throws ConvertEntityDTOException {
        try{
            PhieuHen phieuHen= modelMapper.map(createPhieuHenDTO,PhieuHen.class);

            Optional<KhachHang> opt= khachHangRepository.findById(createPhieuHenDTO.getMaKhachHang());
            KhachHang khachHang;
            if(!opt.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            }
            khachHang= opt.get();
            phieuHen.setKhachHangPH(khachHang);

            Optional<LoaiHinhSuKien> opt1= loaiHinhSuKienRepository.findById(createPhieuHenDTO.getMaLoaiHinhSuKien());
            LoaiHinhSuKien loaiHinhSuKien;
            if(!opt1.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_LOAIHINHSUKIEN_NOT_FOUND);
            }
            loaiHinhSuKien= opt1.get();
            phieuHen.setLoaiHinhSuKienPH(loaiHinhSuKien);
            return phieuHen;

        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

    public List<ViewPhieuHenDTO> convertToListDTO(List<PhieuHen> listEntity) throws ConvertEntityDTOException {
        List<ViewPhieuHenDTO> listDTO;
        try{
            listDTO=listEntity.stream().map(this::convertToDTO).collect(Collectors.toList());
        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
        return listDTO;
    }




}
