package com.example.backend_qlnh.service;

import com.example.backend_qlnh.dto.PhieuHenDTO.CreatePhieuHenDTO;
import com.example.backend_qlnh.dto.PhieuHenDTO.ViewPhieuHenDTO;
import com.example.backend_qlnh.entity.KhachHang;
import com.example.backend_qlnh.entity.PhieuHen;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;

import java.util.List;

public interface PhieuHenService {
    PhieuHen createPhieuHen(CreatePhieuHenDTO createPhieuHenDTO) throws CreateDataFailException;
    PhieuHen updatePhieuHen(ViewPhieuHenDTO viewDetailPhieuHenDTO, Long id) throws UpdateDataFailException, DataNotFoundException;
    void deletePhieuHen(Long id) throws DeleteDataFailException,DataNotFoundException;
    ViewPhieuHenDTO getPhieuHenById(Long id) throws DataNotFoundException;
    List<ViewPhieuHenDTO> getListPhieuHen() throws DataNotFoundException;
}
