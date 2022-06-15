package com.example.backend_qlnh.service;

import com.example.backend_qlnh.dto.KhuyenMaiDTO.CreateKhuyenMaiDTO;
import com.example.backend_qlnh.dto.KhuyenMaiDTO.ViewDetailKhuyenMaiDTO;

import com.example.backend_qlnh.entity.KhuyenMai;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;

import java.util.List;

public interface KhuyenMaiService {
    KhuyenMai createKhuyenMai(CreateKhuyenMaiDTO createKhuyenMaiDTO) throws CreateDataFailException;
    KhuyenMai updateKhuyenMai(ViewDetailKhuyenMaiDTO viewDetailKhuyenMaiDTO, Long id) throws UpdateDataFailException, DataNotFoundException;
    void deleteKhuyenMai(Long id) throws DeleteDataFailException,DataNotFoundException;
    ViewDetailKhuyenMaiDTO getKhuyenMaiById(Long id) throws DataNotFoundException;
    List<ViewDetailKhuyenMaiDTO> getListKhuyenMai() throws DataNotFoundException;
}
