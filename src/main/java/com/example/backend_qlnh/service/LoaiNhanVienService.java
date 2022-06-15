package com.example.backend_qlnh.service;

import com.example.backend_qlnh.dto.LoaiNhanVienDTO.CreateLoaiNhanVienDTO;
import com.example.backend_qlnh.dto.LoaiNhanVienDTO.ViewDetailLoaiNhanVien;
import com.example.backend_qlnh.entity.LoaiNhanVien;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;

import java.util.List;

public interface LoaiNhanVienService {
    LoaiNhanVien createLoaiNhanVien(CreateLoaiNhanVienDTO createLoaiNhanVienDTO) throws CreateDataFailException;
    LoaiNhanVien updateLoaiNhanVien(ViewDetailLoaiNhanVien viewLoaiNhanVienDTO, Long id) throws UpdateDataFailException, DataNotFoundException;
    void deleteLoaiNhanVien(Long id) throws DeleteDataFailException,DataNotFoundException;
    ViewDetailLoaiNhanVien getLoaiNhanVienById(Long id) throws DataNotFoundException;
    List<ViewDetailLoaiNhanVien> getListLoaiNhanVien() throws DataNotFoundException;
}
