package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.entity.HinhAnh;
import com.example.backend_qlnh.entity.NhaHang;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NhaHangRepositoryTest {
    @Autowired
    private NhaHangRepository nhaHangRepository;

    @Autowired
    private HinhAnhRepository hinhAnhRepository;

    @Test
    public void CreateNhaHang() throws Exception{
        NhaHang nhaHang= new NhaHang();
        nhaHang.setMaNhaHang(1L);
        nhaHang.setTenNhaHang("LuckyCenter");
        nhaHang.setEmail("luckycenter@gmail.com");
        nhaHang.setSdt("0123456789");
        nhaHang.setDiaChi("9999 Trường Chinh");
        nhaHang.setDienTich("10000m2");
        HinhAnh hinhAnh= hinhAnhRepository.getById(4l);
        nhaHang.setHinhAnhNH(hinhAnh);
        NhaHang nhaHang1= nhaHangRepository.save(nhaHang);
        Assertions.assertNotNull(nhaHang1);
    }



}
