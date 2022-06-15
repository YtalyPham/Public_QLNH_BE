package com.example.backend_qlnh.service;

import com.example.backend_qlnh.dto.NhaHangDTO.CreateNhaHangDTO;
import com.example.backend_qlnh.dto.NhaHangDTO.ViewNhaHangDTO;
import com.example.backend_qlnh.entity.NhaHang;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;

import java.util.List;

public interface NhaHangService {
    NhaHang createNhaHang(CreateNhaHangDTO createNhaHangDTO) throws CreateDataFailException;
    NhaHang updateNhaHang(ViewNhaHangDTO viewDetailNhaHangDTO, Long id) throws UpdateDataFailException, DataNotFoundException;
    void deleteNhaHang(Long id) throws DeleteDataFailException,DataNotFoundException;
    ViewNhaHangDTO getNhaHangById(Long id) throws DataNotFoundException;
    List<ViewNhaHangDTO> getListNhaHang() throws DataNotFoundException;
}
