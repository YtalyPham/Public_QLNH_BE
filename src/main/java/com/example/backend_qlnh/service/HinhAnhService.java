package com.example.backend_qlnh.service;

import com.example.backend_qlnh.dto.HinhAnhDTO.CreateHinhAnhDTO;
import com.example.backend_qlnh.dto.HinhAnhDTO.ViewDetailHinhAnhDTO;
import com.example.backend_qlnh.entity.HinhAnh;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;

import java.util.List;

public interface HinhAnhService {
    HinhAnh createHinhAnh(CreateHinhAnhDTO createHinhAnhDTO) throws CreateDataFailException;
    HinhAnh updateHinhAnh(ViewDetailHinhAnhDTO viewDetailHinhAnhDTO, Long id) throws UpdateDataFailException,DataNotFoundException;
    void deleteHinhAnh(Long id) throws DeleteDataFailException,DataNotFoundException;
    ViewDetailHinhAnhDTO getHinhAnhById(Long id) throws DataNotFoundException;
    List<ViewDetailHinhAnhDTO> getListHinhAnh() throws DataNotFoundException;



}
