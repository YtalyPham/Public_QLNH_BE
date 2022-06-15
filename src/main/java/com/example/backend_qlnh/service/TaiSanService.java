package com.example.backend_qlnh.service;

import com.example.backend_qlnh.dto.TaiSanDTO.CreateTaiSanDTO;
import com.example.backend_qlnh.dto.TaiSanDTO.ViewTaiSanDTO;
import com.example.backend_qlnh.entity.TaiSan;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;

import java.util.List;

public interface TaiSanService {
    TaiSan createTaiSan(CreateTaiSanDTO createTaiSanDTO) throws CreateDataFailException;
    TaiSan updateTaiSan(ViewTaiSanDTO viewDetailTaiSanDTO, Long id) throws UpdateDataFailException, DataNotFoundException;
    void deleteTaiSan(Long id) throws DeleteDataFailException,DataNotFoundException;
    ViewTaiSanDTO getTaiSanById(Long id) throws DataNotFoundException;
    List<ViewTaiSanDTO> getListTaiSan() throws DataNotFoundException;
}
