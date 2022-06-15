package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.entity.HinhAnh;
import com.example.backend_qlnh.entity.MonAn;
import com.example.backend_qlnh.entity.NguyenLieu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MonAnRepositoryTest {
    @Autowired
    private MonAnRepository monAnRepository;
    @Autowired
    private HinhAnhRepository hinhAnhRepository;
    @Autowired
    private NguyenLieuRepository nguyenLieuRepository;

    @Test
    public void CreateMonAn() throws Exception{
        MonAn monAn= new MonAn();
        monAn.setMaMonAn(1L);
        monAn.setTenMonAn("Súp hoàng đế");
        monAn.setLoaiMonAn("KHAI_VI");
        monAn.setDonGia(1000000.0);
        NguyenLieu nguyenLieu= nguyenLieuRepository.getById(1L);
        monAn.setNguyenLieuMA(nguyenLieu);
        HinhAnh hinhAnh= hinhAnhRepository.getById(7L);
        monAn.setHinhAnhMA(hinhAnh);
        MonAn monAn1= monAnRepository.save(monAn);
        Assertions.assertNotNull(monAn1);

    }



}
