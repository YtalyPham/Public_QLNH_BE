package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.entity.DanhGia;
import com.example.backend_qlnh.entity.KhachHang;
import com.example.backend_qlnh.entity.PhieuDatTiec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang,Long> {
    @Query(value = "SELECT e.* FROM KhachHang e WHERE e.ma_tai_khoan = ?1 ",nativeQuery = true)
    Optional<KhachHang> getKHTheoMaTK(Long ma_tai_khoan);

    @Query(value = "SELECT e.* FROM KhachHang e WHERE e.sdt = ?1 ",nativeQuery = true)
    Optional<KhachHang> getKHTheoSdt(String sdt);
}
