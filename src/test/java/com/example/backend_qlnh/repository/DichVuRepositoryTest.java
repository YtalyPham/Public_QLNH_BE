package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.entity.DichVu;
import com.example.backend_qlnh.entity.HinhAnh;
import com.example.backend_qlnh.exception.DataNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class DichVuRepositoryTest {
    @Autowired
    private DichVuRepository dichVuRepository;
    @Autowired
    private HinhAnhRepository hinhAnhRepository;

    @Test
    public void CreateDichVu() throws Exception{
        DichVu dichVu= new DichVu();
        dichVu.setMaDichVu(1L);
        dichVu.setTenDichVu("Xe hoa cưới");
        dichVu.setGhiChu("trang trí bông hồng");
        dichVu.setSoLuong(1);
        dichVu.setDonGia(500000.0);
        List<HinhAnh> hinhAnhs= new ArrayList<>();
        HinhAnh hinhAnh1= hinhAnhRepository.getById(9L);
        dichVu.setHinhAnhDV(hinhAnh1);
        DichVu dichVu1=dichVuRepository.save(dichVu);
        Assertions.assertNotNull(dichVu1);




    }

}
