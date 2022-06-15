package com.example.backend_qlnh.service;

import com.example.backend_qlnh.dto.SanhTiecDTO.CreateSanhTiecDTO;
import com.example.backend_qlnh.dto.SanhTiecDTO.ViewSanhTiecDTO;
import com.example.backend_qlnh.entity.SanhTiec;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;

import java.util.List;

public interface SanhTiecService {
    SanhTiec createSanhTiec(CreateSanhTiecDTO createSanhTiecDTO) throws CreateDataFailException;
    SanhTiec updateSanhTiec(ViewSanhTiecDTO viewSanhTiecDTO, Long id) throws UpdateDataFailException, DataNotFoundException;
    void deleteSanhTiec(Long id) throws DeleteDataFailException,DataNotFoundException;
    ViewSanhTiecDTO getSanhTiecById(Long id) throws DataNotFoundException;
    List<ViewSanhTiecDTO> getListSanhTiec() throws DataNotFoundException;
    List<ViewSanhTiecDTO> getListSanhTiecKhongTrung(List<Long> mast) throws DataNotFoundException;
}
