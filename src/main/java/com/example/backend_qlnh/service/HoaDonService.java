package com.example.backend_qlnh.service;

import com.example.backend_qlnh.dto.HoaDonDTO.CreateHoaDonDTO;
import com.example.backend_qlnh.dto.HoaDonDTO.ViewHoaDonDTO;
import com.example.backend_qlnh.entity.HoaDon;

import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;

import java.util.List;

public interface HoaDonService {
    HoaDon createHoaDon(CreateHoaDonDTO createHoaDonDTO) throws CreateDataFailException;
    HoaDon updateHoaDon(ViewHoaDonDTO viewDetailHoaDonDTO, Long id) throws UpdateDataFailException, DataNotFoundException;
    void deleteHoaDon(Long id) throws DeleteDataFailException,DataNotFoundException;
    ViewHoaDonDTO getHoaDonById(Long id) throws DataNotFoundException;
    List<ViewHoaDonDTO> getListHoaDon() throws DataNotFoundException;
}
