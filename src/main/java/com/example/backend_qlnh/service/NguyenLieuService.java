package com.example.backend_qlnh.service;

import com.example.backend_qlnh.dto.MonAnDTO.ViewDetailMonAnDTO;
import com.example.backend_qlnh.dto.NguyenLieuDTO.CreateNguyenLieuDTO;
import com.example.backend_qlnh.dto.NguyenLieuDTO.ViewDetailNguyenLieuDTO;
import com.example.backend_qlnh.entity.NguyenLieu;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;

import java.util.List;

public interface NguyenLieuService {
    NguyenLieu createNguyenLieu(CreateNguyenLieuDTO createNguyenLieuDTO) throws CreateDataFailException;
    NguyenLieu updateNguyenLieu(ViewDetailNguyenLieuDTO viewNguyenLieuDTO, Long id) throws UpdateDataFailException, DataNotFoundException;
    void deleteNguyenLieu(Long id) throws DeleteDataFailException,DataNotFoundException;
    ViewDetailNguyenLieuDTO getNguyenLieuById(Long id) throws DataNotFoundException;
    List<ViewDetailNguyenLieuDTO> getListNguyenLieu() throws DataNotFoundException;
    List<ViewDetailNguyenLieuDTO> getListNguyenLieuTheoMonAn(List<Long> manl) throws DataNotFoundException;
}
