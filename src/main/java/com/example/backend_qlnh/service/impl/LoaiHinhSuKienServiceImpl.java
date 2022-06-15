package com.example.backend_qlnh.service.impl;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.converter.LoaiHinhSuKienConverter;
import com.example.backend_qlnh.dto.LoaiHinhSuKienDTO.CreateLoaiHinhSuKienDTO;
import com.example.backend_qlnh.dto.LoaiHinhSuKienDTO.ViewLoaiHinhSuKienDTO;

import com.example.backend_qlnh.entity.LoaiHinhSuKien;

import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.repository.LoaiHinhSuKienRepository;
import com.example.backend_qlnh.service.LoaiHinhSuKienService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoaiHinhSuKienServiceImpl implements LoaiHinhSuKienService {
    @Autowired
    LoaiHinhSuKienConverter loaiHinhSuKienConverter;
    @Autowired
    LoaiHinhSuKienRepository loaiHinhSuKienRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(LoaiHinhSuKienServiceImpl.class);
    @Override
    public LoaiHinhSuKien createLoaiHinhSuKien(CreateLoaiHinhSuKienDTO createLoaiHinhSuKienDTO) throws CreateDataFailException {
        LoaiHinhSuKien loaiHinhSuKien;
        try{
            loaiHinhSuKien=loaiHinhSuKienConverter.convertToEntity(createLoaiHinhSuKienDTO);
            return loaiHinhSuKienRepository.save(loaiHinhSuKien);
        }catch (Exception ex){
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_LOAIHINHSUKIEN_FAIL);
        }
    }

    @Override
    public LoaiHinhSuKien updateLoaiHinhSuKien(ViewLoaiHinhSuKienDTO viewLoaiHinhSuKienDTO, Long id) throws UpdateDataFailException, DataNotFoundException {
        try{
            Optional<LoaiHinhSuKien> opt= loaiHinhSuKienRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("Loại hình sự kiện khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_LOAIHINHSUKIEN_NOT_FOUND);
            }
            LoaiHinhSuKien loaiHinhSuKien= opt.get();
            loaiHinhSuKien.setTenLoaiHinhSuKien(viewLoaiHinhSuKienDTO.getTenLoaiHinhSuKien());
            return loaiHinhSuKienRepository.save(loaiHinhSuKien);
        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_LOAIHINHSUKIEN_FAIL);
        }
    }

    @Override
    public void deleteLoaiHinhSuKien(Long id) throws DeleteDataFailException, DataNotFoundException {
        try{
            Optional<LoaiHinhSuKien> opt= loaiHinhSuKienRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("Loại hình sự kiện khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_LOAIHINHSUKIEN_NOT_FOUND);
            }
            loaiHinhSuKienRepository.deleteById(id);
        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_LOAIHINHSUKIEN_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_LOAIHINHSUKIEN_NOT_FOUND);
            }
        }catch(Exception ex){
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_LOAIHINHSUKIEN_FAIL);
        }
    }

    @Override
    public ViewLoaiHinhSuKienDTO getLoaiHinhSuKienById(Long id) throws DataNotFoundException {
        ViewLoaiHinhSuKienDTO viewDichVuDTO;
        try{
            Optional<LoaiHinhSuKien> opt= loaiHinhSuKienRepository.findById(id);
            if(!opt.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_LOAIHINHSUKIEN_NOT_FOUND);
            }
            LoaiHinhSuKien loaiHinhSuKien= opt.get();
            viewDichVuDTO= loaiHinhSuKienConverter.convertToDTO(loaiHinhSuKien);

        }catch (Exception exception){
            throw new DataNotFoundException(ErrorCode.ERR_LOAIHINHSUKIEN_NOT_FOUND);
        }
        return viewDichVuDTO;
    }

    @Override
    public List<ViewLoaiHinhSuKienDTO> getListLoaiHinhSuKien() throws DataNotFoundException {
        List<ViewLoaiHinhSuKienDTO> listDTO;
        try{
            List<LoaiHinhSuKien> listEntity= loaiHinhSuKienRepository.findAll();
            listDTO=loaiHinhSuKienConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_LOAIHINHSUKIEN_LIST_LOADED_FAIL);
        }
        return listDTO;
    }
}
