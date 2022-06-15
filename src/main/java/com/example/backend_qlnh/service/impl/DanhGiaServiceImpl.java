package com.example.backend_qlnh.service.impl;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.converter.DanhGiaConverter;
import com.example.backend_qlnh.dto.DanhGiaDTO.CreateDanhGiaDTO;
import com.example.backend_qlnh.dto.DanhGiaDTO.ViewDanhGiaDTO;
import com.example.backend_qlnh.entity.DanhGia;
import com.example.backend_qlnh.entity.KhachHang;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.repository.DanhGiaRepository;
import com.example.backend_qlnh.repository.KhachHangRepository;
import com.example.backend_qlnh.service.DanhGiaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DanhGiaServiceImpl implements DanhGiaService {
    @Autowired
    DanhGiaRepository danhGiaRepository;
    @Autowired
    KhachHangRepository khachHangRepository;
    @Autowired
    DanhGiaConverter danhGiaConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(DanhGiaServiceImpl.class);
    @Override
    public DanhGia createDanhGia(CreateDanhGiaDTO createDanhGiaDTO) throws CreateDataFailException {
        DanhGia danhGia;
        try{
            danhGia=danhGiaConverter.convertToEntity(createDanhGiaDTO);

            return danhGiaRepository.save(danhGia);
        }catch (Exception ex){
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_DANHGIA_FAIL);
        }
    }

    @Override
    public DanhGia updateDanhGia(ViewDanhGiaDTO viewDetailDanhGiaDTO, Long id) throws UpdateDataFailException, DataNotFoundException {
        try{
            Optional<DanhGia> opt= danhGiaRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("DanhGia khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_DANHGIA_NOT_FOUND);
            }
            DanhGia danhGia= opt.get();
            danhGia.setSoSao(viewDetailDanhGiaDTO.getSoSao());
            danhGia.setNoiDung(viewDetailDanhGiaDTO.getNoiDung());


            Optional<KhachHang> optKH= khachHangRepository.findById(viewDetailDanhGiaDTO.getMaKhachHang());
            if(!optKH.isPresent()){
                LOGGER.info("KhachHang khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            }
            KhachHang khachHang= optKH.get();
            danhGia.setKhachHangDG(khachHang);

            return danhGiaRepository.save(danhGia);
        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_KHACHHANG_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            }else{
                throw new DataNotFoundException(ErrorCode.ERR_DANHGIA_NOT_FOUND);
            }
        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_DANHGIA_FAIL);
        }
    }

    @Override
    public void deleteDanhGia(Long id) throws DeleteDataFailException, DataNotFoundException {
        try{
            Optional<DanhGia> opt= danhGiaRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("DanhGia khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_DANHGIA_NOT_FOUND);
            }
            danhGiaRepository.deleteById(id);
        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_DANHGIA_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_DANHGIA_NOT_FOUND);
            }
        }catch(Exception ex){
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_DANHGIA_FAIL);
        }
    }

    @Override
    public ViewDanhGiaDTO getDanhGiaById(Long id) throws DataNotFoundException {
        ViewDanhGiaDTO viewDanhGiaDTO;
        try{
            Optional<DanhGia> optDanhGia= danhGiaRepository.findById(id);
            if(!optDanhGia.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_DANHGIA_NOT_FOUND);
            }
            DanhGia danhGia= optDanhGia.get();
            viewDanhGiaDTO= danhGiaConverter.convertToDTO(danhGia);

        }catch (Exception exception){
            throw new DataNotFoundException(ErrorCode.ERR_DANHGIA_NOT_FOUND);
        }
        return viewDanhGiaDTO;
    }

    @Override
    public List<ViewDanhGiaDTO> getListDanhGia() throws DataNotFoundException {
        List<ViewDanhGiaDTO> listDTO;
        try{
            List<DanhGia> listEntity= danhGiaRepository.findAll();
            listDTO=danhGiaConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_DANHGIA_LIST_LOADED_FAIL);
        }
        return listDTO;
    }
}
