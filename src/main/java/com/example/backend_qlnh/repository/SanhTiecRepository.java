package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.entity.DanhGia;
import com.example.backend_qlnh.entity.PhieuDatTiec;
import com.example.backend_qlnh.entity.SanhTiec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SanhTiecRepository extends JpaRepository<SanhTiec,Long> {
    @Query(value = "SELECT e.* FROM SanhTiec e WHERE e.ma_sanh_tiec not in (?1)",nativeQuery = true)
    List<SanhTiec> timSanhTiecKhongTrung(List<Long> ma_sanh_tiec);
}
