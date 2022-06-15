package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.entity.PhieuHen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhieuHenRepository extends JpaRepository<PhieuHen,Long> {
}
