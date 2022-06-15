package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.entity.DanhGia;
import com.example.backend_qlnh.entity.MonAn;
import com.example.backend_qlnh.entity.NguyenLieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NguyenLieuRepository extends JpaRepository<NguyenLieu,Long> {
    @Query(value = "SELECT e.* FROM NguyenLieu e WHERE e.ma_nguyen_lieu in (?1)",nativeQuery = true)
    List<NguyenLieu> timNguyenLieuTheoMA(List<Long> ma_nguyen_lieu);
}
