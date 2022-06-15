package com.example.backend_qlnh.service;

import com.example.backend_qlnh.dto.DichVuDTO.CreateDichVuDTO;
import com.example.backend_qlnh.dto.DichVuDTO.ViewDichVuDTO;
import com.example.backend_qlnh.entity.DichVu;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;

import java.util.List;

public interface DichVuService {
    DichVu createDichVu(CreateDichVuDTO createDichVuDTO) throws CreateDataFailException;
    DichVu updateDichVu(ViewDichVuDTO viewDichVuDTO, Long id) throws UpdateDataFailException, DataNotFoundException;
    void deleteDichVu(Long id) throws DeleteDataFailException,DataNotFoundException;
    ViewDichVuDTO getDichVuById(Long id) throws DataNotFoundException;
    List<ViewDichVuDTO> getListDichVu() throws DataNotFoundException;
    List<ViewDichVuDTO> getListDichvuTheoPDT(List<Long> madv) throws DataNotFoundException;
}
