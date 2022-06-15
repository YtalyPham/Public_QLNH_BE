package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.entity.LoaiHinhSuKien;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoaiHinhSuKienRepositoryTest {
    @Autowired
    private LoaiHinhSuKienRepository loaiHinhSuKienRepository;

    @Test
    public void CreateLoaiHinhSuKien() throws Exception{
        LoaiHinhSuKien loaiHinhSuKien= new LoaiHinhSuKien();
        loaiHinhSuKien.setMaLoaiHinhSuKien(1L);
        loaiHinhSuKien.setTenLoaiHinhSuKien("HOI_NGHI");
        LoaiHinhSuKien loaiHinhSuKien1=loaiHinhSuKienRepository.save(loaiHinhSuKien);
        Assertions.assertNotNull(loaiHinhSuKien1);
    }
    @Test
    public void CreateLoaiHinhSuKien2() throws Exception{
        LoaiHinhSuKien loaiHinhSuKien= new LoaiHinhSuKien();
        loaiHinhSuKien.setMaLoaiHinhSuKien(2L);
        loaiHinhSuKien.setTenLoaiHinhSuKien("TIEC_CUOI");
        LoaiHinhSuKien loaiHinhSuKien1=loaiHinhSuKienRepository.save(loaiHinhSuKien);
        Assertions.assertNotNull(loaiHinhSuKien1);
    }
    @Test
    public void CreateLoaiHinhSuKien3() throws Exception{
        LoaiHinhSuKien loaiHinhSuKien= new LoaiHinhSuKien();
        loaiHinhSuKien.setMaLoaiHinhSuKien(3L);
        loaiHinhSuKien.setTenLoaiHinhSuKien("TIEC_CA_NHAN");
        LoaiHinhSuKien loaiHinhSuKien1=loaiHinhSuKienRepository.save(loaiHinhSuKien);
        Assertions.assertNotNull(loaiHinhSuKien1);
    }
    @Test
    public void CreateLoaiHinhSuKien4() throws Exception{
        LoaiHinhSuKien loaiHinhSuKien= new LoaiHinhSuKien();
        loaiHinhSuKien.setMaLoaiHinhSuKien(4L);
        loaiHinhSuKien.setTenLoaiHinhSuKien("TIEC_DOANH_NGHIEP");
        LoaiHinhSuKien loaiHinhSuKien1=loaiHinhSuKienRepository.save(loaiHinhSuKien);
        Assertions.assertNotNull(loaiHinhSuKien1);
    }
    @Test
    public void CreateLoaiHinhSuKien5() throws Exception{
        LoaiHinhSuKien loaiHinhSuKien= new LoaiHinhSuKien();
        loaiHinhSuKien.setMaLoaiHinhSuKien(5L);
        loaiHinhSuKien.setTenLoaiHinhSuKien("TRIEN_LAM");
        LoaiHinhSuKien loaiHinhSuKien1=loaiHinhSuKienRepository.save(loaiHinhSuKien);
        Assertions.assertNotNull(loaiHinhSuKien1);
    }





}
