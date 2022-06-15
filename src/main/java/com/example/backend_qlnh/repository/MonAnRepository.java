package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.entity.DanhGia;
import com.example.backend_qlnh.entity.DichVu;
import com.example.backend_qlnh.entity.MonAn;
import com.example.backend_qlnh.entity.ThucDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonAnRepository extends JpaRepository<MonAn,Long> {
    @Query(value = "SELECT e.* FROM MonAn e WHERE e.ma_mon_an in (?1)",nativeQuery = true)
    List<MonAn> timMonAnTheoTD(List<Long> ma_mon_an);
    @Query(value = "SELECT e.* FROM MonAn e GROUP BY e.ten_mon_an",nativeQuery = true)
    List<MonAn> getMonAnKhongTrung();
    @Query(value = "SELECT DISTINCT e.ma_nguyen_lieu FROM MonAn e WHERE e.ten_mon_an like ?1",nativeQuery = true)
    List<Long> timMaNguyenLieu(String ten_mon_an);
    @Query(value = "SELECT e.* FROM MonAn e WHERE e.loai_mon_an like (?1) GROUP BY e.ten_mon_an",nativeQuery = true)
    List<MonAn> timMonAnTheoLoaiMonAn(String loai_mon_an);
    @Query(value = "SELECT e.ma_mon_an FROM MonAn e WHERE e.ten_mon_an like ?1",nativeQuery = true)
    List<Long> getListMaMonAn(String ten_mon_an);
    @Query(value = "DELETE FROM MonAn e WHERE e.ma_mon_an in (?1)",nativeQuery = true)
    List<MonAn> xoaNhieuMA(List<Long> ma_mon_an);
//    @Query(value = "SELECT DISTINCT e.ma_nguyen_lieu FROM MonAn e WHERE e.ten_mon_an like ?1",nativeQuery = true)
//    List<MonAn> updateNhieuMA(String ten_mon_an);
}
