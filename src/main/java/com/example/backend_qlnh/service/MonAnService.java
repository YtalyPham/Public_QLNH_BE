package com.example.backend_qlnh.service;

import com.example.backend_qlnh.dto.DichVuDTO.ViewDichVuDTO;
import com.example.backend_qlnh.dto.MonAnDTO.CreateMonAnDTO;

import com.example.backend_qlnh.dto.MonAnDTO.UpdateNhieuMonAnDTO;
import com.example.backend_qlnh.dto.MonAnDTO.ViewDetailMonAnDTO;
import com.example.backend_qlnh.entity.MonAn;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;

import java.util.List;

public interface MonAnService {
    MonAn createMonAn(CreateMonAnDTO createMonAnDTO) throws CreateDataFailException;
    MonAn updateMonAn(ViewDetailMonAnDTO viewMonAnDTO, Long id) throws UpdateDataFailException, DataNotFoundException;
    MonAn updateNhieuMonAn(UpdateNhieuMonAnDTO viewMonAnDTO, Long id) throws UpdateDataFailException, DataNotFoundException;
    void deleteMonAn(Long id) throws DeleteDataFailException,DataNotFoundException;
    ViewDetailMonAnDTO getMonAnById(Long id) throws DataNotFoundException;
    List<ViewDetailMonAnDTO> getListMonAn() throws DataNotFoundException;
    List<ViewDetailMonAnDTO> getListMonAnTheoThucDon(List<Long> mama) throws DataNotFoundException;
    List<ViewDetailMonAnDTO> getListMonAnKhongTrung() throws DataNotFoundException;
    List<Long> getListMaNguyenLieu(String tenMonAn) throws DataNotFoundException;
    List<ViewDetailMonAnDTO> getListMAtheoLoaiMA(String loaimonan) throws DataNotFoundException;
    List<Long> getListMaMonAn(String tenMonAn) throws DataNotFoundException;
}
