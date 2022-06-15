package com.example.backend_qlnh.service;

import com.example.backend_qlnh.dto.LoaiHinhSuKienDTO.CreateLoaiHinhSuKienDTO;
import com.example.backend_qlnh.dto.LoaiHinhSuKienDTO.ViewLoaiHinhSuKienDTO;
import com.example.backend_qlnh.dto.MonAnDTO.CreateMonAnDTO;
import com.example.backend_qlnh.dto.MonAnDTO.ViewDetailMonAnDTO;
import com.example.backend_qlnh.entity.LoaiHinhSuKien;
import com.example.backend_qlnh.entity.MonAn;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;

import java.util.List;

public interface LoaiHinhSuKienService {
    LoaiHinhSuKien createLoaiHinhSuKien(CreateLoaiHinhSuKienDTO createLoaiHinhSuKienDTO) throws CreateDataFailException;
    LoaiHinhSuKien updateLoaiHinhSuKien(ViewLoaiHinhSuKienDTO viewLoaiHinhSuKienDTO, Long id) throws UpdateDataFailException, DataNotFoundException;
    void deleteLoaiHinhSuKien(Long id) throws DeleteDataFailException,DataNotFoundException;
    ViewLoaiHinhSuKienDTO getLoaiHinhSuKienById(Long id) throws DataNotFoundException;
    List<ViewLoaiHinhSuKienDTO> getListLoaiHinhSuKien() throws DataNotFoundException;
}
