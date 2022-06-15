package com.example.backend_qlnh.converter;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.dto.HoaDonDTO.CreateHoaDonDTO;
import com.example.backend_qlnh.dto.HoaDonDTO.ViewHoaDonDTO;
import com.example.backend_qlnh.dto.PhieuDatTiecDTO.CreatePhieuDatTiecDTO;
import com.example.backend_qlnh.dto.PhieuDatTiecDTO.ViewPhieuDatTiecDTO;
import com.example.backend_qlnh.entity.*;
import com.example.backend_qlnh.exception.ConvertEntityDTOException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.repository.HoaDonRepository;
import com.example.backend_qlnh.repository.KhachHangRepository;
import com.example.backend_qlnh.repository.NhaHangRepository;
import com.example.backend_qlnh.repository.NhanVienRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class HoaDonConverter {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    HoaDonRepository hoaDonRepository;
    @Autowired
    KhachHangRepository khachHangRepository;
    @Autowired
    NhanVienRepository nhanVienRepository;
    @Autowired
    NhaHangRepository nhaHangRepository;

    public ViewHoaDonDTO convertToDTO(HoaDon hoaDon){
        ViewHoaDonDTO dto= modelMapper.map(hoaDon, ViewHoaDonDTO.class);
        Long maKH= hoaDon.getKhachHangHD().getMaKhachHang();
        Long maNV= hoaDon.getNhanVienHD().getMaNhanVien();
        Long maNH= hoaDon.getNhaHangHD().getMaNhaHang();

        dto.setMaKhachHang(maKH);
        dto.setMaNhanVien(maNV);
        dto.setMaNhaHang(maNH);
        return dto;
    }

    public HoaDon convertToEntity(CreateHoaDonDTO createHoaDonDTO) throws ConvertEntityDTOException {
        try{
            HoaDon hoaDon= modelMapper.map(createHoaDonDTO,HoaDon.class);

            Optional<NhaHang> opt= nhaHangRepository.findById(createHoaDonDTO.getMaNhaHang());
            NhaHang nhaHang;
            if(!opt.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_NHAHANG_NOT_FOUND);
            }
            nhaHang= opt.get();
            hoaDon.setNhaHangHD(nhaHang);


            Optional<NhanVien> opt3= nhanVienRepository.findById(createHoaDonDTO.getMaNhanVien());
            NhanVien nhanVien;
            if(!opt3.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_NHANVIEN_NOT_FOUND);
            }
            nhanVien= opt3.get();
            hoaDon.setNhanVienHD(nhanVien);

            Optional<KhachHang> opt4= khachHangRepository.findById(createHoaDonDTO.getMaKhachHang());
            KhachHang khachHang;
            if(!opt4.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            }
            khachHang= opt4.get();
            hoaDon.setKhachHangHD(khachHang);

            return hoaDon;

        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

    public List<ViewHoaDonDTO> convertToListDTO(List<HoaDon> listEntity) throws ConvertEntityDTOException {
        List<ViewHoaDonDTO> listDTO;
        try{
            listDTO=listEntity.stream().map(this::convertToDTO).collect(Collectors.toList());
        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
        return listDTO;
    }

}
