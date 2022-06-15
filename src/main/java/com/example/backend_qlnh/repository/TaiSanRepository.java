package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.entity.DanhGia;
import com.example.backend_qlnh.entity.TaiSan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaiSanRepository extends JpaRepository<TaiSan,Long> {
}
