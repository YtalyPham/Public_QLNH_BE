package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.entity.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;

@SpringBootTest
public class PhieuDatTiecRepositoryTest {
    @Autowired
    private PhieuDatTiecRepository phieuDatTiecRepository;
    @Autowired
    private SanhTiecRepository sanhTiecRepository;
    @Autowired
    private ThucDonRepository thucDonRepository;
    @Autowired
    private TapDichVuRepository tapDichVuRepository;
    @Autowired
    private  KhachHangRepository khachHangRepository;
    @Autowired
    private LoaiHinhSuKienRepository loaiHinhSuKienRepository;

    @Test
    public void CreatePhieuDatTiec() throws Exception{
        PhieuDatTiec phieuDatTiec = new PhieuDatTiec();
        phieuDatTiec.setMaPhieuDatTiec(1L);
        phieuDatTiec.setNgayToChuc(LocalDateTime.of(2022, Month.JANUARY,15,0,0,0));
        phieuDatTiec.setLichHen(LocalDateTime.of(2022, Month.JANUARY,6,0,0,0));
        phieuDatTiec.setGhiChu("Tặng thêm 2 bàn");
        phieuDatTiec.setSoLuongBan(100);
        phieuDatTiec.setBuoi("TRUA");
        phieuDatTiec.setThanhToan(true);
        phieuDatTiec.setThanhTien(50000000.0);

        SanhTiec sanhTiec= sanhTiecRepository.getById(1L);
        ThucDon thucDon= thucDonRepository.getById(1L);
        TapDichVu tapDichVu= tapDichVuRepository.getById(1L);
        KhachHang khachHang= khachHangRepository.getById(1L);
        LoaiHinhSuKien loaiHinhSuKien= loaiHinhSuKienRepository.getById(1L);

        phieuDatTiec.setSanhTiecPDT(sanhTiec);
        phieuDatTiec.setThucDonPDT(thucDon);
        phieuDatTiec.setTapDichVuPTD(tapDichVu);
        phieuDatTiec.setKhachHangPDT(khachHang);
        phieuDatTiec.setLoaiHinhSuKienPDT(loaiHinhSuKien);
        PhieuDatTiec phieuDatTiec1= phieuDatTiecRepository.save(phieuDatTiec);
        Assertions.assertNotNull(phieuDatTiec1);

    }

}
