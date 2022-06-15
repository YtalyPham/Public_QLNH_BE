package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.entity.HoaDon;
import com.example.backend_qlnh.entity.KhachHang;
import com.example.backend_qlnh.entity.NhaHang;
import com.example.backend_qlnh.entity.NhanVien;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;

@SpringBootTest
public class HoaDonRepositoryTest {
    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Autowired
    private KhachHangRepository khachHangRepository;
    @Autowired
    private NhanVienRepository nhanVienRepository;
    @Autowired
    private NhaHangRepository nhaHangRepository;

    @Test
    public void CreateHoaDon() throws Exception{
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaHoaDon(1L);
        hoaDon.setNgayLapHoaDon(LocalDateTime.of(2022, Month.JANUARY,15,0,0,0));

        KhachHang khachHang= khachHangRepository.getById(1L);
        NhanVien nhanVien= nhanVienRepository.getById(1L);
        NhaHang nhaHang= nhaHangRepository.getById(1L);

        hoaDon.setKhachHangHD(khachHang);
        hoaDon.setNhaHangHD(nhaHang);
        hoaDon.setNhanVienHD(nhanVien);

        HoaDon hoaDon1= hoaDonRepository.save(hoaDon);
        Assertions.assertNotNull(hoaDon1);
    }

}
