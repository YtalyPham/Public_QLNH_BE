package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.entity.MonAn;
import com.example.backend_qlnh.entity.TapDichVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TapDichVuRepository extends JpaRepository<TapDichVu,Long> {
    @Query(value = "SELECT DISTINCT e.ma_dich_vu FROM TapDichVu e WHERE e.ten_tap_dich_vu like ?1",nativeQuery = true)
    List<Long> timMaDichVu(String ten_tap_dich_vu);
    @Query(value = "SELECT e.* FROM TapDichVu e GROUP BY e.ten_tap_dich_vu",nativeQuery = true)
    List<TapDichVu> getTapDichVuKhongTrung();
}
