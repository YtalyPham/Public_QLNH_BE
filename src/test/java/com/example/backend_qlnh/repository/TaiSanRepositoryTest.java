package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.entity.HinhAnh;
import com.example.backend_qlnh.entity.NhaHang;
import com.example.backend_qlnh.entity.TaiSan;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TaiSanRepositoryTest {
    @Autowired
    private TaiSanRepository taiSanRepository;
    @Autowired
    private HinhAnhRepository hinhAnhRepository;
    @Autowired
    private NhaHangRepository nhaHangRepository;


    @Test
    public void CreateTaiSan() throws Exception{
        TaiSan taiSan= new TaiSan();
        taiSan.setMaTaiSan(1L);
        taiSan.setTenTaiSan("ghế");
        taiSan.setTinhTrang(true);
        taiSan.setSoLuong(15000);
        taiSan.setGhiChu("ổn");
        HinhAnh hinhAnh= hinhAnhRepository.getById(2L);
        taiSan.setHinhAnhTS(hinhAnh);
        NhaHang nhaHang= nhaHangRepository.getById(1L);
        taiSan.setNhahang(nhaHang);
        TaiSan taiSan1= taiSanRepository.save(taiSan);
        Assertions.assertNotNull(taiSan1);

    }



}
