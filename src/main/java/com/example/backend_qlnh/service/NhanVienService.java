package com.example.backend_qlnh.service;

import com.example.backend_qlnh.dto.KhachHangDTO.ViewKhachHangDTO;
import com.example.backend_qlnh.dto.NhanVienDTO.CreateNhanVienDTO;
import com.example.backend_qlnh.dto.NhanVienDTO.ViewNhanVienDTO;

import com.example.backend_qlnh.entity.NhanVien;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;

import java.util.List;

public interface NhanVienService {
    NhanVien createNhanVien(CreateNhanVienDTO createNhanVienDTO) throws CreateDataFailException;
    NhanVien updateNhanVien(ViewNhanVienDTO viewDetailNhanVienDTO, Long id) throws UpdateDataFailException, DataNotFoundException;
    void deleteNhanVien(Long id) throws DeleteDataFailException,DataNotFoundException;
    ViewNhanVienDTO getNhanVienById(Long id) throws DataNotFoundException;
    List<ViewNhanVienDTO> getListNhanVien() throws DataNotFoundException;
    int diemDanh(Long id) throws DataNotFoundException;
    ViewNhanVienDTO getNVByMaTK(Long id) throws DataNotFoundException;
    Integer demslNhanVien() throws  DataNotFoundException;

}
