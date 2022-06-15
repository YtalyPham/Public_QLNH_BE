package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.entity.HinhAnh;
import com.example.backend_qlnh.entity.LoaiNhanVien;
import com.example.backend_qlnh.entity.NhanVien;
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
public class NhanVienRepositoryTest {
    @Autowired
    private NhanVienRepository nhanVienRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HinhAnhRepository hinhAnhRepository;
    @Autowired
    private LoaiNhanVienRepository loaiNhanVienRepository;

    @Test
    public void CreateNhanVien() throws Exception{
        NhanVien nhanVien= new NhanVien();
        nhanVien.setMaNhanVien(1L);
        nhanVien.setTenNhanVien("Ân");
        nhanVien.setEmail("An@gmail.com");
        nhanVien.setCmnd("123444555");
        nhanVien.setGioiTinh(true);
        nhanVien.setNgaySinh(LocalDateTime.of(2002, Month.JANUARY,15,0,0,0));
        nhanVien.setNgayThue(LocalDateTime.of(2022, Month.JANUARY,15,0,0,0));
        nhanVien.setSdt("123456789");
        nhanVien.setDiaChi("555 Quang Trung");
        nhanVien.setLuongCoBan(25000.0);
        nhanVien.setNgayLam(24);
        nhanVien.setNgayNghi(0);

        LoaiNhanVien loaiNhanVien= loaiNhanVienRepository.getById(8l);
        nhanVien.setLoaiNhanVienNV(loaiNhanVien);

        Optional<User> taiKhoan1= userRepository.findById(2l);
        User user;
        if (taiKhoan1.isPresent()){
            user = taiKhoan1.get();
        } else throw new DataNotFoundException(ErrorCode.ERR_TAIKHOAN_NOT_FOUND);
        nhanVien.setTaiKhoanNV(user);

        Optional<HinhAnh> hinhAnh1=  hinhAnhRepository.findById(5L);
        HinhAnh hinhAnh;
        if (hinhAnh1.isPresent()){
            hinhAnh= hinhAnh1.get();
        } else throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
        nhanVien.setHinhAnhNV(hinhAnh);

        NhanVien nhanVien1= nhanVienRepository.save(nhanVien);

        Assertions.assertNotNull(nhanVien1);


    }



}
