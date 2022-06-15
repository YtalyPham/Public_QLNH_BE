package com.example.backend_qlnh.service.impl;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.converter.LoaiNhanVienConverter;
import com.example.backend_qlnh.dto.LoaiNhanVienDTO.CreateLoaiNhanVienDTO;
import com.example.backend_qlnh.dto.LoaiNhanVienDTO.ViewDetailLoaiNhanVien;
import com.example.backend_qlnh.dto.NguyenLieuDTO.ViewDetailNguyenLieuDTO;
import com.example.backend_qlnh.entity.LoaiNhanVien;
import com.example.backend_qlnh.entity.NguyenLieu;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.repository.LoaiNhanVienRepository;
import com.example.backend_qlnh.service.LoaiNhanVienService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiNhanVienServiceImpl implements LoaiNhanVienService {

    @Autowired
    LoaiNhanVienRepository loaiNhanVienRepository;
    @Autowired
    LoaiNhanVienConverter loaiNhanVienConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoaiNhanVienServiceImpl.class);
    @Override
    public LoaiNhanVien createLoaiNhanVien(CreateLoaiNhanVienDTO createLoaiNhanVienDTO) throws CreateDataFailException {
        LoaiNhanVien loaiNhanVien;
        try{
            loaiNhanVien=loaiNhanVienConverter.convertToEntity(createLoaiNhanVienDTO);
            return loaiNhanVienRepository.save(loaiNhanVien);
        }catch (Exception ex){
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_LOAINHANVIEN_FAIL);
        }
    }

    @Override
    public LoaiNhanVien updateLoaiNhanVien(ViewDetailLoaiNhanVien viewLoaiNhanVienDTO, Long id) throws UpdateDataFailException, DataNotFoundException {
        try{
            Optional<LoaiNhanVien> opt= loaiNhanVienRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("Loại nhân viên khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_LOAINHANVIEN_NOT_FOUND);
            }
            LoaiNhanVien loaiNhanVien= opt.get();
            loaiNhanVien.setTenLoai(viewLoaiNhanVienDTO.getTenLoai());
            return loaiNhanVienRepository.save(loaiNhanVien);
        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_LOAINHANVIEN_FAIL);
        }
    }

    @Override
    public void deleteLoaiNhanVien(Long id) throws DeleteDataFailException, DataNotFoundException {
        try{
            Optional<LoaiNhanVien> opt= loaiNhanVienRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("Loại nhân viên khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_LOAINHANVIEN_NOT_FOUND);
            }
            loaiNhanVienRepository.deleteById(id);
        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_LOAINHANVIEN_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_LOAINHANVIEN_NOT_FOUND);
            }
        }catch(Exception ex){
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_LOAINHANVIEN_FAIL);
        }
    }

    @Override
    public ViewDetailLoaiNhanVien getLoaiNhanVienById(Long id) throws DataNotFoundException {
        ViewDetailLoaiNhanVien viewDichVuDTO;
        try{
            Optional<LoaiNhanVien> opt= loaiNhanVienRepository.findById(id);
            if(!opt.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_LOAINHANVIEN_NOT_FOUND);
            }
            LoaiNhanVien loaiNhanVien= opt.get();
            viewDichVuDTO= loaiNhanVienConverter.convertToDTO(loaiNhanVien);

        }catch (Exception exception){
            throw new DataNotFoundException(ErrorCode.ERR_LOAINHANVIEN_NOT_FOUND);
        }
        return viewDichVuDTO;
    }

    @Override
    public List<ViewDetailLoaiNhanVien> getListLoaiNhanVien() throws DataNotFoundException {
        List<ViewDetailLoaiNhanVien> listDTO;
        try{
            List<LoaiNhanVien> listEntity= loaiNhanVienRepository.findAll();
            listDTO=loaiNhanVienConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_LOAINHANVIEN_LIST_LOADED_FAIL);
        }
        return listDTO;
    }
}
