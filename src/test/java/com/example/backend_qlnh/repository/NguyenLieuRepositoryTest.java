package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.entity.NguyenLieu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;

@SpringBootTest
public class NguyenLieuRepositoryTest {
    @Autowired
    private NguyenLieuRepository nguyenLieuRepository;

    @Test
    public void CreateNguyenLieu() throws Exception{
        NguyenLieu nguyenLieu= new NguyenLieu();
        nguyenLieu.setMaNguyenLieu(1L);
        nguyenLieu.setTenNguyenLieu("Thịt");
        nguyenLieu.setDonGiaMua(80000.0);
        nguyenLieu.setGhiChu("Đã mua");
        nguyenLieu.setKg(20.5);
        nguyenLieu.setNgayNhap(LocalDateTime.of(2022, Month.JANUARY,1,0,0,0));
        nguyenLieu.setNgayHetHan(LocalDateTime.of(2022, Month.JANUARY,15,0,0,0));
        NguyenLieu nguyenLieu1= nguyenLieuRepository.save(nguyenLieu);
        Assertions.assertNotNull(nguyenLieu1);

    }
}
