package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.entity.HinhAnh;
import com.example.backend_qlnh.entity.MonAn;
import com.example.backend_qlnh.entity.ThucDon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ThucDonRepositoryTest {
    @Autowired
    private ThucDonRepository thucDonRepository;
    @Autowired
    private HinhAnhRepository hinhAnhRepository;
    @Autowired
    private MonAnRepository monAnRepository;

    @Test
    public void CreateThucDon() throws Exception{
        ThucDon thucDon= new ThucDon();
        thucDon.setMaThucDon(1L);
        thucDon.setTenThucDon("Set1");
        thucDon.setSetThucDon(1);
        HinhAnh hinhAnh = hinhAnhRepository.getById(8L);
        thucDon.setHinhAnhTD(hinhAnh);
        MonAn monAn= monAnRepository.getById(1L);
        thucDon.setMonAnTD(monAn);
        thucDon.setDonGia(2500000.0);
        ThucDon thucDon1= thucDonRepository.save(thucDon);
        Assertions.assertNotNull(thucDon1);






    }





}
