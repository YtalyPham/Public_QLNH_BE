package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.entity.ChiTietHoaDon;
import com.example.backend_qlnh.entity.HoaDon;
import com.example.backend_qlnh.entity.KhuyenMai;
import com.example.backend_qlnh.entity.PhieuDatTiec;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ChiTietHoaDonRepositoryTest {
    @Autowired
    private ChiTietHoaDonRepository chiTietHoaDonRepository;
    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Autowired
    private PhieuDatTiecRepository phieuDatTiecRepository;
    @Autowired
    private KhuyenMaiRepository khuyenMaiRepository;

    @Test
    public void CreateCTHD() throws Exception{
        ChiTietHoaDon chiTietHoaDon= new ChiTietHoaDon();
        chiTietHoaDon.setMaCthd(1L);
        chiTietHoaDon.setThanhTien(22800000.0);
        chiTietHoaDon.setTongTien(25000000.0);
        chiTietHoaDon.setTienKhachDua(25000000.0);
        chiTietHoaDon.setTienThoi(0.0);
        chiTietHoaDon.setVAT(0.1);
        chiTietHoaDon.setThanhToan(true);

        HoaDon hoaDon= hoaDonRepository.getById(1L);
        PhieuDatTiec phieuDatTiec= phieuDatTiecRepository.getById(1L);
        KhuyenMai khuyenMai= khuyenMaiRepository.getById(1L);

        chiTietHoaDon.setHoaDonCTHD(hoaDon);
        chiTietHoaDon.setKhuyenMaiCTHD(khuyenMai);
        chiTietHoaDon.setPhieuDatTiecCTHD(phieuDatTiec);
        ChiTietHoaDon chiTietHoaDon1= chiTietHoaDonRepository.save(chiTietHoaDon);
        Assertions.assertNotNull(chiTietHoaDon1);
    }
}
