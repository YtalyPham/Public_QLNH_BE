package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.entity.LoaiNhanVien;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoaiNhanVienRepositoryTest {
    @Autowired
    private LoaiNhanVienRepository loaiNhanVienRepository;

    @Test
    public void CreateLoaiNhanVien1() throws Exception{
        LoaiNhanVien loaiNhanVien= new LoaiNhanVien();
        loaiNhanVien.setMaLoaiNhanVien(1L);
        loaiNhanVien.setTenLoai("BEP");
        LoaiNhanVien loaiNhanVien1= loaiNhanVienRepository.save(loaiNhanVien);
        Assertions.assertNotNull(loaiNhanVien1);
    }

    @Test
    public void CreateLoaiNhanVien2() throws Exception{
        LoaiNhanVien loaiNhanVien= new LoaiNhanVien();
        loaiNhanVien.setMaLoaiNhanVien(2L);
        loaiNhanVien.setTenLoai("BEP_TRUONG");
        LoaiNhanVien loaiNhanVien1= loaiNhanVienRepository.save(loaiNhanVien);
        Assertions.assertNotNull(loaiNhanVien1);
    }

    @Test
    public void CreateLoaiNhanVien3() throws Exception{
        LoaiNhanVien loaiNhanVien= new LoaiNhanVien();
        loaiNhanVien.setMaLoaiNhanVien(3L);
        loaiNhanVien.setTenLoai("QUAN_LY");
        LoaiNhanVien loaiNhanVien1= loaiNhanVienRepository.save(loaiNhanVien);
        Assertions.assertNotNull(loaiNhanVien1);
    }

    @Test
    public void CreateLoaiNhanVien4() throws Exception{
        LoaiNhanVien loaiNhanVien= new LoaiNhanVien();
        loaiNhanVien.setMaLoaiNhanVien(4L);
        loaiNhanVien.setTenLoai("LE_TAN");
        LoaiNhanVien loaiNhanVien1= loaiNhanVienRepository.save(loaiNhanVien);
        Assertions.assertNotNull(loaiNhanVien1);
    }

    @Test
    public void CreateLoaiNhanVien5() throws Exception{
        LoaiNhanVien loaiNhanVien= new LoaiNhanVien();
        loaiNhanVien.setMaLoaiNhanVien(5L);
        loaiNhanVien.setTenLoai("BOI_BAN");
        LoaiNhanVien loaiNhanVien1= loaiNhanVienRepository.save(loaiNhanVien);
        Assertions.assertNotNull(loaiNhanVien1);
    }

    @Test
    public void CreateLoaiNhanVien6() throws Exception{
        LoaiNhanVien loaiNhanVien= new LoaiNhanVien();
        loaiNhanVien.setMaLoaiNhanVien(6L);
        loaiNhanVien.setTenLoai("TU_VAN_KHACH_HANG");
        LoaiNhanVien loaiNhanVien1= loaiNhanVienRepository.save(loaiNhanVien);
        Assertions.assertNotNull(loaiNhanVien1);
    }

    @Test
    public void CreateLoaiNhanVien7() throws Exception{
        LoaiNhanVien loaiNhanVien= new LoaiNhanVien();
        loaiNhanVien.setMaLoaiNhanVien(7L);
        loaiNhanVien.setTenLoai("LAO_CONG");
        LoaiNhanVien loaiNhanVien1= loaiNhanVienRepository.save(loaiNhanVien);
        Assertions.assertNotNull(loaiNhanVien1);
    }

    @Test
    public void CreateLoaiNhanVien8() throws Exception{
        LoaiNhanVien loaiNhanVien= new LoaiNhanVien();
        loaiNhanVien.setMaLoaiNhanVien(8L);
        loaiNhanVien.setTenLoai("THU_NGAN");
        LoaiNhanVien loaiNhanVien1= loaiNhanVienRepository.save(loaiNhanVien);
        Assertions.assertNotNull(loaiNhanVien1);
    }


}
