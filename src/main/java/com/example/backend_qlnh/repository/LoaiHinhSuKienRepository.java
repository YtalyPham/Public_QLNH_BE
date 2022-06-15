package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.entity.LoaiHinhSuKien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiHinhSuKienRepository extends JpaRepository<LoaiHinhSuKien,Long> {
}
