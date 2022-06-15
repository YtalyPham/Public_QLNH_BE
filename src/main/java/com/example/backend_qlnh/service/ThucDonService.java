package com.example.backend_qlnh.service;

import com.example.backend_qlnh.dto.MonAnDTO.CreateMonAnDTO;
import com.example.backend_qlnh.dto.MonAnDTO.ViewDetailMonAnDTO;
import com.example.backend_qlnh.dto.ThucDonDTO.CreateThucDonDTO;
import com.example.backend_qlnh.dto.ThucDonDTO.UpdateNhieuThucDonDTO;
import com.example.backend_qlnh.dto.ThucDonDTO.ViewThucDonDTO;
import com.example.backend_qlnh.entity.MonAn;
import com.example.backend_qlnh.entity.ThucDon;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;

import java.time.LocalDateTime;
import java.util.List;

public interface ThucDonService {
    ThucDon createThucDon(CreateThucDonDTO createThucDonDTO) throws CreateDataFailException;
    ThucDon updateThucDon(ViewThucDonDTO viewThucDonDTO, Long id) throws UpdateDataFailException, DataNotFoundException;
    ThucDon updateNhieuThucDon(UpdateNhieuThucDonDTO viewThucDonDTO, Long id) throws UpdateDataFailException, DataNotFoundException;
    void deleteThucDon(Long id) throws DeleteDataFailException,DataNotFoundException;
    ViewThucDonDTO getThucDonById(Long id) throws DataNotFoundException;
    List<ViewThucDonDTO> getListThucDon() throws DataNotFoundException;
    List<Long> getListMaMonAn(String tenThucDon) throws DataNotFoundException;
    List<Long> getListMaThucDonTheoTenTD(String tenThucDon) throws DataNotFoundException;
    List<ViewThucDonDTO> getListThucDonKhongTrung() throws DataNotFoundException;
}
