package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.entity.HinhAnh;
import com.example.backend_qlnh.entity.KhuyenMai;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;

@SpringBootTest
public class KhuyenMaiRepositoryTest {
    @Autowired
    private KhuyenMaiRepository khuyenMaiRepository;

    @Autowired
    private HinhAnhRepository hinhAnhRepository;

    @Test
    public void CreateKhuyenMai() throws Exception{
        KhuyenMai khuyenMai= new KhuyenMai();
        khuyenMai.setMaKhuyenMai(1L);
        khuyenMai.setTenKhuyenMai("Mùa cưới rộn ràng");
        khuyenMai.setGiamGia(10.0);
        khuyenMai.setNoiDung("Khuyến mãi hết tháng 1");
        khuyenMai.setThoiGianBatDau(LocalDateTime.of(2020, Month.JANUARY,1,0,0,0));
        khuyenMai.setThoiGianKetThuc(LocalDateTime.of(2020, Month.JANUARY,31,0,0,0));

        HinhAnh hinhAnh= hinhAnhRepository.getById(3l);
        khuyenMai.setHinhAnhKM(hinhAnh);
        KhuyenMai khuyenMai1= khuyenMaiRepository.save(khuyenMai);
        Assertions.assertNotNull(khuyenMai1);

    }




}
