package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.entity.DanhGia;
import com.example.backend_qlnh.entity.HinhAnh;
import com.example.backend_qlnh.entity.KhachHang;
import com.example.backend_qlnh.exception.DataNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class DanhGiaRepositoryTest {
    @Autowired
    private DanhGiaRepository danhGiaRepository;
    @Autowired
    private KhachHangRepository khachHangRepository;

    @Test
    public void CreateDanhGia() throws Exception{
        DanhGia danhGia= new DanhGia();
        danhGia.setMaDanhGia(1L);
        Optional<KhachHang> khachHang=  khachHangRepository.findById(1L);
        KhachHang khachHang1;
        if (khachHang.isPresent()){
            khachHang1= khachHang.get();
        } else throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
        danhGia.setKhachHangDG(khachHang1);
        danhGia.setNoiDung("asdf");
        danhGia.setSoSao(5);

        DanhGia danhGia1= danhGiaRepository.save(danhGia);
        Assertions.assertNotNull(danhGia1);

    }
}
