package com.example.backend_qlnh.service;


import com.example.backend_qlnh.dto.TapDichVuDTO.CreateTapDichVuDTO;
import com.example.backend_qlnh.entity.TapDichVu;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;

import java.util.List;

public interface TapDichVuService {
    TapDichVu createTapDichVu(CreateTapDichVuDTO createTapDichVuDTO) throws CreateDataFailException;
    TapDichVu updateTapDichVu(CreateTapDichVuDTO viewTapDichVuDTO, Long id) throws UpdateDataFailException, DataNotFoundException;
    void deleteTapDichVu(Long id) throws DeleteDataFailException,DataNotFoundException;
    CreateTapDichVuDTO getTapDichVuById(Long id) throws DataNotFoundException;
    List<CreateTapDichVuDTO> getListTapDichVu() throws DataNotFoundException;
    List<Long> getListMaDichVu(String tenTapDichVu) throws DataNotFoundException;
    List<CreateTapDichVuDTO> getListTapDichVuKhongTrung() throws DataNotFoundException;
    
}
