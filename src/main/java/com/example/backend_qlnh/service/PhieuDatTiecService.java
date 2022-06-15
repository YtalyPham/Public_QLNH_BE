package com.example.backend_qlnh.service;


import com.example.backend_qlnh.dto.PhieuDatTiecDTO.CreatePhieuDatTiecDTO;
import com.example.backend_qlnh.dto.PhieuDatTiecDTO.ViewPhieuDatTiecDTO;
import com.example.backend_qlnh.entity.PhieuDatTiec;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;

import java.time.LocalDateTime;
import java.util.List;

public interface PhieuDatTiecService {
    PhieuDatTiec createPhieuDatTiec(CreatePhieuDatTiecDTO createPhieuDatTiecDTO) throws CreateDataFailException;
    PhieuDatTiec updatePhieuDatTiec(ViewPhieuDatTiecDTO viewDetailPhieuDatTiecDTO, Long id) throws UpdateDataFailException, DataNotFoundException;
    void deletePhieuDatTiec(Long id) throws DeleteDataFailException,DataNotFoundException;
    ViewPhieuDatTiecDTO getPhieuDatTiecById(Long id) throws DataNotFoundException;
    List<ViewPhieuDatTiecDTO> getListPhieuDatTiec() throws DataNotFoundException;
    List<ViewPhieuDatTiecDTO> getListPhieuDatTiecTheoMaKH(Long makh) throws DataNotFoundException;
    List<ViewPhieuDatTiecDTO> getListPDTDaThanhToanTheoMaKH(Long makh) throws DataNotFoundException;
    List<ViewPhieuDatTiecDTO> getListPDTChuaThanhToanTheoMaKH(Long makh) throws DataNotFoundException;
    List<ViewPhieuDatTiecDTO> getListPhieuDatTiecTheoMakhNgay(Long makh, LocalDateTime ngayDat) throws DataNotFoundException;//khong hop ly
    List<ViewPhieuDatTiecDTO> getListPhieuDatTiecTheoMakhNgayBuoi(Long makh, LocalDateTime ngayDat,String buoi) throws DataNotFoundException;
    List<Long> getListMaSanhTiecTheoNgayBuoi(LocalDateTime ngayDat,String buoi) throws DataNotFoundException;
    Integer getPDTchuaTT(Long mkh) throws  DataNotFoundException;
    List<ViewPhieuDatTiecDTO> getListPhieuDatTiecDaTT() throws DataNotFoundException;
    List<ViewPhieuDatTiecDTO> getListPhieuDatTiecChuaTT() throws DataNotFoundException;
    PhieuDatTiec updateLichHenPhieuDatTiec(LocalDateTime lichHen,Long id) throws UpdateDataFailException;
    PhieuDatTiec updateThanhToanPhieuDatTiec(Long id) throws UpdateDataFailException;
    PhieuDatTiec updateTienCocPhieuDatTiec(Double tiencoc,Long id) throws UpdateDataFailException;
    List<Long> getPDTTheoNgayTC(String ngaytochuc) throws DataNotFoundException;
    List<ViewPhieuDatTiecDTO> getListPhieuDatTiecTheoListMaPDT(List<Long> malistid) throws DataNotFoundException;
    Integer demslPDTchuaTT() throws  DataNotFoundException;
    Integer demslPDTdaTT() throws  DataNotFoundException;
    Integer demslPDTtochuchomnay(String ngay) throws  DataNotFoundException;

}
