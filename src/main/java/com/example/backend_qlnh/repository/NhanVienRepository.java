package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.entity.DanhGia;
import com.example.backend_qlnh.entity.KhachHang;
import com.example.backend_qlnh.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien,Long> {
    @Query(value = "SELECT e.* FROM NhanVien e WHERE e.ma_tai_khoan = ?1 ",nativeQuery = true)
    Optional<NhanVien> getNVTheoMaTK(Long ma_tai_khoan);

    @Query(value = "Select COUNT(ma_nhan_vien) from NhanVien",nativeQuery = true)
    Integer demSlNhanVien();
}
