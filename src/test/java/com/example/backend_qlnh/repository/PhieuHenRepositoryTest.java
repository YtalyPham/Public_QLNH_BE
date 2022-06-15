package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.entity.KhachHang;
import com.example.backend_qlnh.entity.LoaiHinhSuKien;
import com.example.backend_qlnh.entity.PhieuHen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;

@SpringBootTest
public class PhieuHenRepositoryTest {
    @Autowired
    PhieuHenRepository phieuHenRepository;
    @Autowired
    LoaiHinhSuKienRepository loaiHinhSuKienRepository;
    @Autowired
    KhachHangRepository khachHangRepository;

    @Test
    public void CreatePhieuHen() throws Exception{
        PhieuHen phieuHen= new PhieuHen();

        phieuHen.setMaPhieuHen(1L);
        phieuHen.setNgayDatTiec(LocalDateTime.of(2022, Month.JANUARY,15,0,0,0));
        phieuHen.setLichHen(LocalDateTime.of(2022, Month.JANUARY,6,0,0,0));
        phieuHen.setGhiChu("Tặng thêm 2 bàn");
        phieuHen.setSoLuongBan(100);
        phieuHen.setBuoi("SANG");

        KhachHang khachHang= khachHangRepository.getById(1L);
        LoaiHinhSuKien loaiHinhSuKien= loaiHinhSuKienRepository.getById(1L);

        phieuHen.setKhachHangPH(khachHang);
        phieuHen.setLoaiHinhSuKienPH(loaiHinhSuKien);
        PhieuHen phieuHen1= phieuHenRepository.save(phieuHen);
        Assertions.assertNotNull(phieuHen1);

    }







}
