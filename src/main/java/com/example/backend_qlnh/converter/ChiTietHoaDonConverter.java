package com.example.backend_qlnh.converter;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.dto.CTHDDTO.CreateChiTietHoaDonDTO;
import com.example.backend_qlnh.dto.CTHDDTO.ViewChiTietHoaDonDTO;
import com.example.backend_qlnh.dto.HoaDonDTO.CreateHoaDonDTO;
import com.example.backend_qlnh.dto.HoaDonDTO.ViewHoaDonDTO;
import com.example.backend_qlnh.entity.*;
import com.example.backend_qlnh.exception.ConvertEntityDTOException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.repository.ChiTietHoaDonRepository;
import com.example.backend_qlnh.repository.HoaDonRepository;
import com.example.backend_qlnh.repository.KhuyenMaiRepository;
import com.example.backend_qlnh.repository.PhieuDatTiecRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ChiTietHoaDonConverter {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    ChiTietHoaDonRepository chiTietHoaDonRepository;
    @Autowired
    KhuyenMaiRepository khuyenMaiRepository;
    @Autowired
    PhieuDatTiecRepository phieuDatTiecRepository;
    @Autowired
    HoaDonRepository hoaDonRepository;

    public ViewChiTietHoaDonDTO convertToDTO(ChiTietHoaDon chiTietHoaDon){
        ViewChiTietHoaDonDTO dto= modelMapper.map(chiTietHoaDon, ViewChiTietHoaDonDTO.class);
        Long maPDT= chiTietHoaDon.getPhieuDatTiecCTHD().getMaPhieuDatTiec();
        Long maKM= chiTietHoaDon.getKhuyenMaiCTHD().getMaKhuyenMai();
        Long maHD= chiTietHoaDon.getHoaDonCTHD().getMaHoaDon();
        dto.setMaPhieuDatTiec(maPDT);
        dto.setMaKhuyenMai(maKM);
        dto.setMaHoaDon(maHD);
        return dto;
    }

    public ChiTietHoaDon convertToEntity(CreateChiTietHoaDonDTO createChiTietHoaDonDTO) throws ConvertEntityDTOException {
        try{
            ChiTietHoaDon chiTietHoaDon= modelMapper.map(createChiTietHoaDonDTO,ChiTietHoaDon.class);

            Optional<PhieuDatTiec> opt= phieuDatTiecRepository.findById(createChiTietHoaDonDTO.getMaPhieuDatTiec());
            PhieuDatTiec phieuDatTiec;
            if(!opt.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
            }
            phieuDatTiec= opt.get();
            chiTietHoaDon.setPhieuDatTiecCTHD(phieuDatTiec);

            Optional<HoaDon> opt2= hoaDonRepository.findById(createChiTietHoaDonDTO.getMaHoaDon());
            HoaDon hoaDon;
            if(!opt.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_HOADON_NOT_FOUND);
            }
            hoaDon= opt2.get();
            chiTietHoaDon.setHoaDonCTHD(hoaDon);

            Optional<KhuyenMai> opt3= khuyenMaiRepository.findById(createChiTietHoaDonDTO.getMaKhuyenMai());
            KhuyenMai khuyenMai;
            if(!opt3.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_KHUYENMAI_NOT_FOUND);
            }
            khuyenMai= opt3.get();
            chiTietHoaDon.setKhuyenMaiCTHD(khuyenMai);

            return chiTietHoaDon;

        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

    public List<ViewChiTietHoaDonDTO> convertToListDTO(List<ChiTietHoaDon> listEntity) throws ConvertEntityDTOException {
        List<ViewChiTietHoaDonDTO> listDTO;
        try{
            listDTO=listEntity.stream().map(this::convertToDTO).collect(Collectors.toList());
        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
        return listDTO;
    }
}
