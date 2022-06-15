package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.entity.ChiTietHoaDon;
import com.example.backend_qlnh.entity.DichVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChiTietHoaDonRepository extends JpaRepository<ChiTietHoaDon,Long> {
    @Query(value = "SELECT e.* FROM ChiTietHoaDon e WHERE e.ma_phieu_dat_tiec in (?1)",nativeQuery = true)
    Optional<ChiTietHoaDon> timCTHDtheoPDT(Long ma_phieu_dat_tiec);

    @Query(value = "SELECT e.* FROM ChiTietHoaDon e WHERE e.ma_cthd in (?1)",nativeQuery = true)
    List<ChiTietHoaDon> timCTHDtheoListId(List<Long> ma_cthd);

    @Query(value = "SELECT e.* FROM ChiTietHoaDon e WHERE e.ngay_lap_hoa_don like ?1",nativeQuery = true)
    List<Long> timCTHDtheoNgay(String ngay_lap_hoa_don);

    @Query(value = "SELECT e.* FROM ChiTietHoaDon e WHERE e.ngay_lap_hoa_don like ?1",nativeQuery = true)
    List<Long> timCTHDtheoThang(String ngay_lap_hoa_don);

    @Query(value = "SELECT e.* FROM ChiTietHoaDon e WHERE e.ngay_lap_hoa_don like ?1",nativeQuery = true)
    List<Long> timCTHDtheoNam(String ngay_lap_hoa_don);

    @Query(value = "SELECT e.* FROM ChiTietHoaDon e WHERE e.ngay_lap_hoa_don BETWEEN ?1 AND ?2",nativeQuery = true)
    List<Long> timCTHDtheoKhoang(String ngay_dau,String ngay_sau);
}
