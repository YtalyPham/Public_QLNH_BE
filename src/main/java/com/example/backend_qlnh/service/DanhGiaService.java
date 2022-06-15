package com.example.backend_qlnh.service;

import com.example.backend_qlnh.dto.DanhGiaDTO.CreateDanhGiaDTO;
import com.example.backend_qlnh.dto.DanhGiaDTO.ViewDanhGiaDTO;

import com.example.backend_qlnh.entity.DanhGia;

import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;

import java.util.List;

public interface DanhGiaService {
    DanhGia createDanhGia(CreateDanhGiaDTO createDanhGiaDTO) throws CreateDataFailException;
    DanhGia updateDanhGia(ViewDanhGiaDTO viewDetailDanhGiaDTO, Long id) throws UpdateDataFailException, DataNotFoundException;
    void deleteDanhGia(Long id) throws DeleteDataFailException,DataNotFoundException;
    ViewDanhGiaDTO getDanhGiaById(Long id) throws DataNotFoundException;
    List<ViewDanhGiaDTO> getListDanhGia() throws DataNotFoundException;
}
