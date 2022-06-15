package com.example.backend_qlnh.service.impl;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.converter.DichVuConverter;
import com.example.backend_qlnh.dto.DichVuDTO.CreateDichVuDTO;
import com.example.backend_qlnh.dto.DichVuDTO.ViewDichVuDTO;
import com.example.backend_qlnh.dto.SanhTiecDTO.ViewSanhTiecDTO;
import com.example.backend_qlnh.entity.DichVu;
import com.example.backend_qlnh.entity.HinhAnh;
import com.example.backend_qlnh.entity.SanhTiec;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.repository.DichVuRepository;
import com.example.backend_qlnh.repository.HinhAnhRepository;
import com.example.backend_qlnh.service.DichVuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DichVuServiceImpl implements DichVuService {
    @Autowired
    DichVuConverter dichVuConverter;
    @Autowired
    DichVuRepository dichVuRepository;
    @Autowired
    HinhAnhRepository hinhAnhRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(DichVuServiceImpl.class);
    @Override
    public DichVu createDichVu(CreateDichVuDTO createDichVuDTO) throws CreateDataFailException {
        DichVu dichVu;
        try{
            dichVu=dichVuConverter.convertToEntity(createDichVuDTO);
            return dichVuRepository.save(dichVu);
        }catch (Exception ex){
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_DICHVU_FAIL);
        }
    }

    @Override
    public DichVu updateDichVu(ViewDichVuDTO viewDichVuDTO, Long id) throws UpdateDataFailException, DataNotFoundException {
        try{
            Optional<DichVu> opt= dichVuRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("Dịch vụ khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_DICHVU_NOT_FOUND);
            }
            DichVu dichVu= opt.get();
            dichVu.setTenDichVu(viewDichVuDTO.getTenDichVu());
            dichVu.setDonGia(viewDichVuDTO.getDonGia());
            dichVu.setSoLuong(viewDichVuDTO.getSoLuong());
            dichVu.setGhiChu(viewDichVuDTO.getGhiChu());

            if(viewDichVuDTO.getMaHinhAnh()==null){
                dichVu.setHinhAnhDV(null);
            }else{
                Optional<HinhAnh> optHinhAnh= hinhAnhRepository.findById(viewDichVuDTO.getMaHinhAnh());
                if(!optHinhAnh.isPresent()){
                    LOGGER.info("HinhAnh khong tim thay: {}",id);
                    throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
                }
                HinhAnh hinhAnh= optHinhAnh.get();
                dichVu.setHinhAnhDV(hinhAnh);
            }

            return dichVuRepository.save(dichVu);
        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_HINHANH_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
            }else{
                throw new DataNotFoundException(ErrorCode.ERR_DICHVU_NOT_FOUND);
            }

        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_DICHVU_FAIL);
        }
    }

    @Override
    public void deleteDichVu(Long id) throws DeleteDataFailException, DataNotFoundException {
        try{
            Optional<DichVu> opt= dichVuRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("Dịch vụ khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_DICHVU_NOT_FOUND);
            }
            dichVuRepository.deleteById(id);
        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_DICHVU_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_DICHVU_NOT_FOUND);
            }
        }catch(Exception ex){
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_DICHVU_FAIL);
        }
    }

    @Override
    public ViewDichVuDTO getDichVuById(Long id) throws DataNotFoundException {
        ViewDichVuDTO viewDichVuDTO;
        try{
            Optional<DichVu> opt= dichVuRepository.findById(id);
            if(!opt.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_DICHVU_NOT_FOUND);
            }
            DichVu dichVu= opt.get();
            viewDichVuDTO= dichVuConverter.convertToDTO(dichVu);

        }catch (Exception exception){
            throw new DataNotFoundException(ErrorCode.ERR_DICHVU_NOT_FOUND);
        }
        return viewDichVuDTO;
    }

    @Override
    public List<ViewDichVuDTO> getListDichVu() throws DataNotFoundException {
        List<ViewDichVuDTO> listDTO;
        try{
            List<DichVu> listEntity= dichVuRepository.findAll();
            listDTO=dichVuConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_DICHVU_LIST_LOADED_FAIL);
        }
        return listDTO;
    }

    @Override
    public List<ViewDichVuDTO> getListDichvuTheoPDT(List<Long> madv) throws DataNotFoundException {
        List<ViewDichVuDTO> listDTO;
        try{
            List<DichVu> listEntity= dichVuRepository.timDichvuTheoPDT(madv);
            listDTO=dichVuConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_DICHVU_LIST_LOADED_FAIL);
        }
        return listDTO;
    }
}
