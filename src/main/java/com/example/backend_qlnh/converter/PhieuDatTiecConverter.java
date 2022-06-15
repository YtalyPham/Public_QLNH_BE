package com.example.backend_qlnh.converter;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.dto.NhanVienDTO.CreateNhanVienDTO;
import com.example.backend_qlnh.dto.NhanVienDTO.ViewNhanVienDTO;
import com.example.backend_qlnh.dto.PhieuDatTiecDTO.CreatePhieuDatTiecDTO;
import com.example.backend_qlnh.dto.PhieuDatTiecDTO.ViewPhieuDatTiecDTO;
import com.example.backend_qlnh.entity.*;
import com.example.backend_qlnh.exception.ConvertEntityDTOException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PhieuDatTiecConverter {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PhieuDatTiecRepository phieuDatTiecRepository;
    @Autowired
    TapDichVuRepository tapDichVuRepository;
    @Autowired
    ThucDonRepository thucDonRepository;
    @Autowired
    LoaiHinhSuKienRepository loaiHinhSuKienRepository;
    @Autowired
    SanhTiecRepository sanhTiecRepository;
    @Autowired
    KhachHangRepository khachHangRepository;

    public ViewPhieuDatTiecDTO convertToDTO(PhieuDatTiec phieuDatTiec){
        ViewPhieuDatTiecDTO dto= modelMapper.map(phieuDatTiec, ViewPhieuDatTiecDTO.class);
        Long maTDV= phieuDatTiec.getTapDichVuPTD().getMaTapDichVu();
        Long maTD= phieuDatTiec.getThucDonPDT().getMaThucDon();
        Long maLhsk= phieuDatTiec.getLoaiHinhSuKienPDT().getMaLoaiHinhSuKien();
        Long maST= phieuDatTiec.getSanhTiecPDT().getMaSanhTiec();
        Long maKH= phieuDatTiec.getKhachHangPDT().getMaKhachHang();
        dto.setMaTapDichVu(maTDV);
        dto.setMaThucDon(maTD);
        dto.setMaLoaiHinhSuKien(maLhsk);
        dto.setMaSanhTiec(maST);
        dto.setMaKhachHang(maKH);
        return dto;
    }

    public PhieuDatTiec convertToEntity(CreatePhieuDatTiecDTO createPhieuDatTiecDTO) throws ConvertEntityDTOException {
        try{
            PhieuDatTiec phieuDatTiec= modelMapper.map(createPhieuDatTiecDTO,PhieuDatTiec.class);

            Optional<TapDichVu> opt= tapDichVuRepository.findById(createPhieuDatTiecDTO.getMaTapDichVu());
            TapDichVu tapDichVu;
            if(!opt.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_TAPDICHVU_NOT_FOUND);
            }
            tapDichVu= opt.get();
            phieuDatTiec.setTapDichVuPTD(tapDichVu);

            Optional<ThucDon> opt1= thucDonRepository.findById(createPhieuDatTiecDTO.getMaThucDon());
            ThucDon thucDon;
            if(!opt1.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_THUCDON_NOT_FOUND);
            }
            thucDon= opt1.get();
            phieuDatTiec.setThucDonPDT(thucDon);

            Optional<LoaiHinhSuKien> opt2= loaiHinhSuKienRepository.findById(createPhieuDatTiecDTO.getMaLoaiHinhSuKien());
            LoaiHinhSuKien loaiHinhSuKien;
            if(!opt2.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_LOAIHINHSUKIEN_NOT_FOUND);
            }
            loaiHinhSuKien= opt2.get();
            phieuDatTiec.setLoaiHinhSuKienPDT(loaiHinhSuKien);

            Optional<SanhTiec> opt3= sanhTiecRepository.findById(createPhieuDatTiecDTO.getMaSanhTiec());
            SanhTiec sanhTiec;
            if(!opt3.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_SANHTIEC_NOT_FOUND);
            }
            sanhTiec= opt3.get();
            phieuDatTiec.setSanhTiecPDT(sanhTiec);

            Optional<KhachHang> opt4= khachHangRepository.findById(createPhieuDatTiecDTO.getMaKhachHang());
            KhachHang khachHang;
            if(!opt4.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            }
            khachHang= opt4.get();
            phieuDatTiec.setKhachHangPDT(khachHang);

            return phieuDatTiec;

        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

    public List<ViewPhieuDatTiecDTO> convertToListDTO(List<PhieuDatTiec> listEntity) throws ConvertEntityDTOException {
        List<ViewPhieuDatTiecDTO> listDTO;
        try{
            listDTO=listEntity.stream().map(this::convertToDTO).collect(Collectors.toList());
        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
        return listDTO;
    }



}
