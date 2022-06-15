package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.entity.HinhAnh;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HinhAnhRepositoryTest {
    @Autowired
    HinhAnhRepository hinhAnhRepository;

    @Test
    public void CreateHinhAnh1() throws Exception{
        HinhAnh hinhAnh= new HinhAnh();
        hinhAnh.setMaHinhAnh(1L);
        hinhAnh.setMaKey("123");
        hinhAnh.setMaUrl("asdfqwer123");
        HinhAnh hinhAnh1= hinhAnhRepository.save(hinhAnh);
        Assertions.assertNotNull(hinhAnh1);
    }

    @Test
    public void CreateHinhAnh2() throws Exception{
        HinhAnh hinhAnh= new HinhAnh();
        hinhAnh.setMaHinhAnh(2L);
        hinhAnh.setMaKey("123");
        hinhAnh.setMaUrl("asdfqwer123");
        HinhAnh hinhAnh1= hinhAnhRepository.save(hinhAnh);
        Assertions.assertNotNull(hinhAnh1);
    }

    @Test
    public void CreateHinhAnh3() throws Exception{
        HinhAnh hinhAnh= new HinhAnh();
        hinhAnh.setMaHinhAnh(3L);
        hinhAnh.setMaKey("123");
        hinhAnh.setMaUrl("asdfqwer123");
        HinhAnh hinhAnh1= hinhAnhRepository.save(hinhAnh);
        Assertions.assertNotNull(hinhAnh1);
    }

    @Test
    public void CreateHinhAnh4() throws Exception{
        HinhAnh hinhAnh= new HinhAnh();
        hinhAnh.setMaHinhAnh(4L);
        hinhAnh.setMaKey("123");
        hinhAnh.setMaUrl("asdfqwer123");
        HinhAnh hinhAnh1= hinhAnhRepository.save(hinhAnh);
        Assertions.assertNotNull(hinhAnh1);
    }

    @Test
    public void CreateHinhAnh5() throws Exception{
        HinhAnh hinhAnh= new HinhAnh();
        hinhAnh.setMaHinhAnh(5L);
        hinhAnh.setMaKey("123");
        hinhAnh.setMaUrl("asdfqwer123");
        HinhAnh hinhAnh1= hinhAnhRepository.save(hinhAnh);
        Assertions.assertNotNull(hinhAnh1);
    }

    @Test
    public void CreateHinhAnh6() throws Exception{
        HinhAnh hinhAnh= new HinhAnh();
        hinhAnh.setMaHinhAnh(6L);
        hinhAnh.setMaKey("123");
        hinhAnh.setMaUrl("asdfqwer123");
        HinhAnh hinhAnh1= hinhAnhRepository.save(hinhAnh);
        Assertions.assertNotNull(hinhAnh1);
    }

    @Test
    public void CreateHinhAnh7() throws Exception{
        HinhAnh hinhAnh= new HinhAnh();
        hinhAnh.setMaHinhAnh(7L);
        hinhAnh.setMaKey("123");
        hinhAnh.setMaUrl("asdfqwer123");
        HinhAnh hinhAnh1= hinhAnhRepository.save(hinhAnh);
        Assertions.assertNotNull(hinhAnh1);
    }

    @Test
    public void CreateHinhAnh8() throws Exception{
        HinhAnh hinhAnh= new HinhAnh();
        hinhAnh.setMaHinhAnh(8L);
        hinhAnh.setMaKey("123");
        hinhAnh.setMaUrl("asdfqwer123");
        HinhAnh hinhAnh1= hinhAnhRepository.save(hinhAnh);
        Assertions.assertNotNull(hinhAnh1);
    }

    @Test
    public void CreateHinhAnh9() throws Exception{
        HinhAnh hinhAnh= new HinhAnh();
        hinhAnh.setMaHinhAnh(9L);
        hinhAnh.setMaKey("123");
        hinhAnh.setMaUrl("asdfqwer123");
        HinhAnh hinhAnh1= hinhAnhRepository.save(hinhAnh);
        Assertions.assertNotNull(hinhAnh1);
    }


}
