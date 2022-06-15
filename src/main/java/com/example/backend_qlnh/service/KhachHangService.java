package com.example.backend_qlnh.service;

import com.example.backend_qlnh.dto.KhachHangDTO.CreateKhachHangDTO;
import com.example.backend_qlnh.dto.KhachHangDTO.ViewKhachHangDTO;
import com.example.backend_qlnh.dto.TaiSanDTO.CreateTaiSanDTO;
import com.example.backend_qlnh.dto.TaiSanDTO.ViewTaiSanDTO;
import com.example.backend_qlnh.entity.KhachHang;
import com.example.backend_qlnh.entity.TaiSan;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;

import java.util.List;

public interface KhachHangService {
    KhachHang createKhachHang(CreateKhachHangDTO createKhachHangDTO) throws CreateDataFailException;
    KhachHang updateKhachHang(ViewKhachHangDTO viewDetailKhachHangDTO, Long id) throws UpdateDataFailException, DataNotFoundException;
    void deleteKhachHang(Long id) throws DeleteDataFailException,DataNotFoundException;
    ViewKhachHangDTO getKhachHangById(Long id) throws DataNotFoundException;
    List<ViewKhachHangDTO> getListKhachHang() throws DataNotFoundException;
    ViewKhachHangDTO getKHByMaTK(Long id) throws DataNotFoundException;
    Boolean getKHBySdt(String sdt) throws DataNotFoundException;
}
