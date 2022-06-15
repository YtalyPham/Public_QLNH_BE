package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.entity.HinhAnh;
import com.example.backend_qlnh.entity.SanhTiec;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SanhTiecRepositoryTest {
    @Autowired
    private SanhTiecRepository sanhTiecRepository;
    @Autowired
    private HinhAnhRepository hinhAnhRepository;
    @Test
    public void CreateSanhTiec() throws Exception{
        SanhTiec sanhTiec= new SanhTiec();
        sanhTiec.setMaSanhTiec(1L);
        sanhTiec.setTenSanhTiec("QueenCenter");
        sanhTiec.setDienTich("1000m2");
        sanhTiec.setDonGia(2000000.0);
        sanhTiec.setKichThuoc("100x20x30");
        sanhTiec.setQuayTrienLam("10m2");
        sanhTiec.setViTri("Tầng 2");
        sanhTiec.setKieuBanDai("80");
        sanhTiec.setKieuBanTron("100");
        sanhTiec.setKieuLopHoc("80");
        sanhTiec.setKieuRapHat("80");
        HinhAnh hinhAnh= hinhAnhRepository.getById(6L);
        sanhTiec.setHinhAnhST(hinhAnh);
        SanhTiec sanhTiec1= sanhTiecRepository.save(sanhTiec);
        Assertions.assertNotNull(sanhTiec1);
    }
}
