package com.example.backend_qlnh.repository;


import com.example.backend_qlnh.entity.PhieuDatTiec;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PhieuDatTiecRepository extends JpaRepository<PhieuDatTiec,Long> {
    @Query(value = "SELECT e.* FROM PhieuDatTiec e WHERE e.ma_khach_hang = ?1 AND e.ngay_to_chuc = ?2",nativeQuery = true)
    List<PhieuDatTiec> timMaKHAndNgayDatTiec(Long ma_khach_hang, LocalDateTime ngay_to_chuc);

    @Query(value = "SELECT e.* FROM PhieuDatTiec e WHERE e.ma_khach_hang = ?1",nativeQuery = true)
    List<PhieuDatTiec> timPDTTheoMaKH(Long ma_khach_hang);

    @Query(value = "SELECT e.* FROM PhieuDatTiec e WHERE e.ma_khach_hang = ?1 and e.thanh_toan=false",nativeQuery = true)
    List<PhieuDatTiec> timPDTChuaTTTheoMaKH(Long ma_khach_hang);

    @Query(value = "SELECT e.* FROM PhieuDatTiec e WHERE e.ma_khach_hang = ?1 and e.thanh_toan=true",nativeQuery = true)
    List<PhieuDatTiec> timPDTDaTTTheoMaKH(Long ma_khach_hang);

    @Query(value = "SELECT e.* FROM PhieuDatTiec e WHERE e.thanh_toan = true",nativeQuery = true)
    List<PhieuDatTiec> timPDTDaThanhToan();

    @Query(value = "SELECT e.* FROM PhieuDatTiec e WHERE e.thanh_toan = false",nativeQuery = true)
    List<PhieuDatTiec> timPDTChuaThanhToan();

    @Query(value = "SELECT e.* FROM PhieuDatTiec e WHERE e.ma_khach_hang = ?1 AND e.ngay_to_chuc = ?2 AND e.buoi =?3",nativeQuery = true)
    List<PhieuDatTiec> timMaKHAndNgayDatTiecAndBuoi(Long ma_khach_hang, LocalDateTime ngay_to_chuc,String buoi);

    @Query(value = "SELECT DISTINCT e.ma_sanh_tiec FROM PhieuDatTiec e WHERE e.ngay_to_chuc = ?1 AND e.buoi =?2",nativeQuery = true)
    List<Long> timMaSTTheoNgayDatTiecAndBuoi(LocalDateTime ngay_to_chuc,String buoi);

    @Query(value = "Select COUNT(ma_phieu_dat_tiec) from PhieuDatTiec where ma_khach_hang = ?1 AND thanh_toan = false",nativeQuery = true)
    Integer timPDTchuaTT(Long ma_khach_hang);

    @Query(value = "UPDATE PhieuDatTiec e SET e.lich_hen = ?1 WHERE e.ma_phieu_dat_tiec = ?2",nativeQuery = true)
    PhieuDatTiec updateLichHenPDT(LocalDateTime lich_hen,Long ma_phieu_dat_tiec);

    @Query(value = "UPDATE PhieuDatTiec e SET e.thanh_toan = true WHERE e.ma_phieu_dat_tiec = ?1",nativeQuery = true)
    PhieuDatTiec updateThanhToanPDT(Long ma_phieu_dat_tiec);

    @Query(value = "SELECT e.* FROM PhieuDatTiec e WHERE e.thanh_toan = true AND e.ngay_to_chuc like ?1",nativeQuery = true)
    List<Long> timPDTtheoNgayTC(String ngay_to_chuc);

    @Query(value = "SELECT e.* FROM PhieuDatTiec e WHERE e.ma_phieu_dat_tiec in (?1)",nativeQuery = true)
    List<PhieuDatTiec> getPDTTheoListMaPDT(List<Long> ma_phieu_dat_tiec);

    @Query(value = "Select COUNT(ma_phieu_dat_tiec) from PhieuDatTiec where thanh_toan = false",nativeQuery = true)
    Integer demPDTchuaTT();

    @Query(value = "Select COUNT(ma_phieu_dat_tiec) from PhieuDatTiec where thanh_toan = true",nativeQuery = true)
    Integer demPDTdaTT();

    @Query(value = "Select COUNT(ma_phieu_dat_tiec) from PhieuDatTiec e where e.thanh_toan=true and e.ngay_to_chuc like ?1",nativeQuery = true)
    Integer demPDTtrongHomNay(String ngay_to_chuc);


//    @Query(value = "SELECT DISTINCT e.ma_dich_vu FROM PhieuDatTiec e WHERE e.ma_khach_hang = ?1 AND e.ngay_to_chuc = ?2 AND e.buoi =?3",nativeQuery = true)
//    List<Long> timMaDVTheoMaKHAndNgayDatTiecAndBuoi(Long ma_khach_hang, LocalDateTime ngay_to_chuc,String buoi);

}
