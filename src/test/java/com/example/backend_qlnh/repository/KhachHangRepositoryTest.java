package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.entity.HinhAnh;
import com.example.backend_qlnh.entity.KhachHang;
import com.example.backend_qlnh.entity.User;
import com.example.backend_qlnh.exception.DataNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

@SpringBootTest
public class KhachHangRepositoryTest {
    @Autowired
    private KhachHangRepository khachHangRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HinhAnhRepository hinhAnhRepository;

    @Test
    public void CreateKhachHang() throws Exception{
        KhachHang khachHang= new KhachHang();
        khachHang.setMaKhachHang(1L);
        khachHang.setTenKhachHang("An");
        khachHang.setEmail("An@gmail.com");
        khachHang.setDiemTichLuy(10);
        khachHang.setGioiTinh(true);
        khachHang.setNgaySinh(LocalDateTime.of(2002, Month.JANUARY,15,0,0,0));
        khachHang.setSdt("123456789");
        khachHang.setVangLai(true);

        Optional<User> taiKhoan1= userRepository.findById(1l);
        User user;
        if (taiKhoan1.isPresent()){
            user = taiKhoan1.get();
        } else throw new DataNotFoundException(ErrorCode.ERR_TAIKHOAN_NOT_FOUND);
        khachHang.setTaiKhoanKH(user);

        Optional<HinhAnh> hinhAnh1=  hinhAnhRepository.findById(1L);
        HinhAnh hinhAnh;
        if (hinhAnh1.isPresent()){
            hinhAnh= hinhAnh1.get();
        } else throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
        khachHang.setHinhAnhKH(hinhAnh);

        KhachHang khachHang1= khachHangRepository.save(khachHang);

        Assertions.assertNotNull(khachHang1);


    }
}
