package com.example.backend_qlnh.service;

import com.example.backend_qlnh.dto.CTHDDTO.CreateChiTietHoaDonDTO;
import com.example.backend_qlnh.dto.CTHDDTO.ViewChiTietHoaDonDTO;
import com.example.backend_qlnh.entity.ChiTietHoaDon;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;

import java.util.List;

public interface ChiTietHoaDonService {
    ChiTietHoaDon createChiTietHoaDon(CreateChiTietHoaDonDTO createChiTietHoaDonDTO) throws CreateDataFailException;
    ChiTietHoaDon updateChiTietHoaDon(ViewChiTietHoaDonDTO viewDetailChiTietHoaDonDTO, Long id) throws UpdateDataFailException, DataNotFoundException;
    void deleteChiTietHoaDon(Long id) throws DeleteDataFailException,DataNotFoundException;
    ViewChiTietHoaDonDTO getChiTietHoaDonById(Long id) throws DataNotFoundException;
    List<ViewChiTietHoaDonDTO> getListChiTietHoaDon() throws DataNotFoundException;
    ViewChiTietHoaDonDTO getCthdByIdPDT(Long id) throws DataNotFoundException;
    List<Long> getCthdTheoNgay(String ngaylaphoadon) throws DataNotFoundException;
    List<Long> getCthdTheoThang(String ngaylaphoadon) throws DataNotFoundException;
    List<Long> getCthdTheoNam(String ngaylaphoadon) throws DataNotFoundException;
    List<Long> getCthdTheoKhoang(String ngaydau,String ngaysau) throws DataNotFoundException;
    List<ViewChiTietHoaDonDTO> getListCthdTheoListId(List<Long> listid) throws DataNotFoundException;
}
