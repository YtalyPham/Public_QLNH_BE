package com.example.backend_qlnh.service.impl;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.converter.TapDichVuConverter;
import com.example.backend_qlnh.dto.TapDichVuDTO.CreateTapDichVuDTO;
import com.example.backend_qlnh.entity.*;
import com.example.backend_qlnh.entity.TapDichVu;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.repository.DichVuRepository;
import com.example.backend_qlnh.repository.TapDichVuRepository;
import com.example.backend_qlnh.service.TapDichVuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TapDichVuServiceImpl implements TapDichVuService {
    @Autowired
    TapDichVuRepository tapDichVuRepository;
    @Autowired
    TapDichVuConverter tapDichVuConverter;

    @Autowired
    DichVuRepository dichVuRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(TapDichVuServiceImpl.class);
    @Override
    public TapDichVu createTapDichVu(CreateTapDichVuDTO createTapDichVuDTO) throws CreateDataFailException {
        TapDichVu tapDichVu;
        try{
            tapDichVu=tapDichVuConverter.convertToEntity(createTapDichVuDTO);
            return tapDichVuRepository.save(tapDichVu);
        }catch (Exception ex){
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_TAPDICHVU_FAIL);
        }
    }

    @Override
    public TapDichVu updateTapDichVu(CreateTapDichVuDTO CreateTapDichVuDTO, Long id) throws UpdateDataFailException, DataNotFoundException {
        try{
            Optional<TapDichVu> opt= tapDichVuRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("Tap dich vu khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_TAPDICHVU_NOT_FOUND);
            }
            TapDichVu tapDichVu= opt.get();
            tapDichVu.setTenTapDichVu(CreateTapDichVuDTO.getTenTapDichVu());

            Optional<DichVu> optDichVu= dichVuRepository.findById(CreateTapDichVuDTO.getMaDichVu());
            if(!optDichVu.isPresent()){
                LOGGER.info("DichVu khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_DICHVU_NOT_FOUND);
            }
            DichVu dichVu= optDichVu.get();
            tapDichVu.setDichVuTDV(dichVu);

            return tapDichVuRepository.save(tapDichVu);

        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_TAPDICHVU_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_TAPDICHVU_NOT_FOUND);
            }else{
                throw new DataNotFoundException(ErrorCode.ERR_DICHVU_NOT_FOUND);
            }
        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_TAPDICHVU_FAIL);
        }
    }

    @Override
    public void deleteTapDichVu(Long id) throws DeleteDataFailException, DataNotFoundException {
        try{
            Optional<TapDichVu> opt= tapDichVuRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("TapDichVu khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_TAPDICHVU_NOT_FOUND);
            }
            tapDichVuRepository.deleteById(id);
        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_TAPDICHVU_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_TAPDICHVU_NOT_FOUND);
            }
        }catch(Exception ex){
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_TAPDICHVU_FAIL);
        }
    }

    @Override
    public CreateTapDichVuDTO getTapDichVuById(Long id) throws DataNotFoundException {
        CreateTapDichVuDTO CreateTapDichVuDTO;
        try{
            Optional<TapDichVu> optTapDichVu= tapDichVuRepository.findById(id);
            if(!optTapDichVu.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_TAPDICHVU_NOT_FOUND);
            }
            TapDichVu tapDichVu= optTapDichVu.get();
            CreateTapDichVuDTO= tapDichVuConverter.convertToDTO(tapDichVu);

        }catch (Exception exception){
            throw new DataNotFoundException(ErrorCode.ERR_TAPDICHVU_NOT_FOUND);
        }
        return CreateTapDichVuDTO;
    }

    @Override
    public List<CreateTapDichVuDTO> getListTapDichVu() throws DataNotFoundException {
        List<CreateTapDichVuDTO> listDTO;
        try{
            List<TapDichVu> listEntity= tapDichVuRepository.findAll();
            listDTO=tapDichVuConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_TAPDICHVU_LIST_LOADED_FAIL);
        }
        return listDTO;
    }

    @Override
    public List<Long> getListMaDichVu(String tenTapDichVu) throws DataNotFoundException {
        List<Long> listDTO;
        try{
            listDTO= tapDichVuRepository.timMaDichVu(tenTapDichVu);

        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_TAPDICHVU_LIST_LOADED_FAIL);
        }
        return listDTO;
    }

    @Override
    public List<CreateTapDichVuDTO> getListTapDichVuKhongTrung() throws DataNotFoundException {
        List<CreateTapDichVuDTO> listDTO;
        try{
            List<TapDichVu> listEntity= tapDichVuRepository.getTapDichVuKhongTrung();
            listDTO=tapDichVuConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_TAPDICHVU_LIST_LOADED_FAIL);
        }
        return listDTO;
    }


}
