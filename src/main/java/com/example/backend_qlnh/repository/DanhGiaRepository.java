package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.entity.DanhGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DanhGiaRepository extends JpaRepository<DanhGia,Long> {
}
