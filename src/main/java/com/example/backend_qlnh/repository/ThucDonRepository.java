package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.entity.DanhGia;
import com.example.backend_qlnh.entity.ThucDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ThucDonRepository extends JpaRepository<ThucDon,Long> {
    @Query(value = "SELECT DISTINCT e.ma_mon_an FROM ThucDon e WHERE e.ten_thuc_don like ?1",nativeQuery = true)
    List<Long> timMaMonAn(String ten_thuc_don);
    @Query(value = "SELECT e.* FROM ThucDon e WHERE e.mac_dinh=true GROUP BY e.ten_thuc_don",nativeQuery = true)
    List<ThucDon> getThucDonKhongTrung();
    @Query(value = "SELECT e.ma_thuc_don FROM ThucDon e WHERE e.ten_thuc_don like ?1",nativeQuery = true)
    List<Long> getNhieuMaThucDonTheoTen(String ten_thuc_don);

}
