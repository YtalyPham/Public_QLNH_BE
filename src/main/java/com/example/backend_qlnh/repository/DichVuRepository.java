package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.entity.DanhGia;
import com.example.backend_qlnh.entity.DichVu;
import com.example.backend_qlnh.entity.SanhTiec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DichVuRepository extends JpaRepository<DichVu,Long> {
    @Query(value = "SELECT e.* FROM DichVu e WHERE e.ma_dich_vu in (?1)",nativeQuery = true)
    List<DichVu> timDichvuTheoPDT(List<Long> ma_dich_vu);
}
