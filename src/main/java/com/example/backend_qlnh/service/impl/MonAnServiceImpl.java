package com.example.backend_qlnh.service.impl;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.converter.MonAnConverter;
import com.example.backend_qlnh.dto.DichVuDTO.ViewDichVuDTO;
import com.example.backend_qlnh.dto.MonAnDTO.CreateMonAnDTO;
import com.example.backend_qlnh.dto.MonAnDTO.UpdateNhieuMonAnDTO;
import com.example.backend_qlnh.dto.MonAnDTO.ViewDetailMonAnDTO;
import com.example.backend_qlnh.dto.TaiSanDTO.ViewTaiSanDTO;
import com.example.backend_qlnh.entity.*;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.repository.HinhAnhRepository;
import com.example.backend_qlnh.repository.MonAnRepository;
import com.example.backend_qlnh.repository.NguyenLieuRepository;
import com.example.backend_qlnh.service.MonAnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MonAnServiceImpl implements MonAnService {
    @Autowired
    MonAnConverter monAnConverter;
    @Autowired
    MonAnRepository monAnRepository;
    @Autowired
    HinhAnhRepository hinhAnhRepository;
    @Autowired
    NguyenLieuRepository nguyenLieuRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(MonAnServiceImpl.class);
    @Override
    public MonAn createMonAn(CreateMonAnDTO createMonAnDTO) throws CreateDataFailException {
        MonAn monAn;
        try{
            monAn=monAnConverter.convertToEntity(createMonAnDTO);
            return monAnRepository.save(monAn);
        }catch (Exception ex){
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_MONAN_FAIL);
        }
    }

    @Override
    public MonAn updateMonAn(ViewDetailMonAnDTO viewMonAnDTO, Long id) throws UpdateDataFailException, DataNotFoundException {
        try{
            Optional<MonAn> opt= monAnRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("Mon an khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_MONAN_NOT_FOUND);
            }
            MonAn monAn= opt.get();
            monAn.setTenMonAn(viewMonAnDTO.getTenMonAn());
            monAn.setLoaiMonAn(viewMonAnDTO.getLoaiMonAn());
            monAn.setDonGia(viewMonAnDTO.getDonGia());

            Optional<HinhAnh> optHinhAnh= hinhAnhRepository.findById(viewMonAnDTO.getMaHinhAnh());
            if(!optHinhAnh.isPresent()){
                LOGGER.info("HinhAnh khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
            }
            HinhAnh hinhAnh= optHinhAnh.get();
            monAn.setHinhAnhMA(hinhAnh);

            Optional<NguyenLieu> optNguyenLieu= nguyenLieuRepository.findById(viewMonAnDTO.getMaNguyenLieu());
            if(!optNguyenLieu.isPresent()){
                LOGGER.info("Nguyen lieu khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_NGUYENLIEU_NOT_FOUND);
            }
            NguyenLieu nguyenLieu= optNguyenLieu.get();
            monAn.setNguyenLieuMA(nguyenLieu);

            return monAnRepository.save(monAn);

        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_NGUYENLIEU_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_NGUYENLIEU_NOT_FOUND);
            }else if(message.equals(ErrorCode.ERR_HINHANH_NOT_FOUND)){
                throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
            }else{
                throw new DataNotFoundException(ErrorCode.ERR_MONAN_NOT_FOUND);
            }

        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_MONAN_FAIL);
        }
    }

    @Override
    public MonAn updateNhieuMonAn(UpdateNhieuMonAnDTO viewMonAnDTO, Long id) throws UpdateDataFailException, DataNotFoundException {
        try{
            Optional<MonAn> opt= monAnRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("Mon an khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_MONAN_NOT_FOUND);
            }
            MonAn monAn= opt.get();
            monAn.setTenMonAn(viewMonAnDTO.getTenMonAn());
            monAn.setLoaiMonAn(viewMonAnDTO.getLoaiMonAn());
            monAn.setDonGia(viewMonAnDTO.getDonGia());
            monAn.setNguyenLieuMA(monAn.getNguyenLieuMA());
            monAn.setHinhAnhMA(monAn.getHinhAnhMA());
            return monAnRepository.save(monAn);

        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_NGUYENLIEU_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_NGUYENLIEU_NOT_FOUND);
            }else if(message.equals(ErrorCode.ERR_HINHANH_NOT_FOUND)){
                throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
            }else{
                throw new DataNotFoundException(ErrorCode.ERR_MONAN_NOT_FOUND);
            }

        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_MONAN_FAIL);
        }
    }

    @Override
    public void deleteMonAn(Long id) throws DeleteDataFailException, DataNotFoundException {
        try{
            Optional<MonAn> opt= monAnRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("MonAn khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_MONAN_NOT_FOUND);
            }
            monAnRepository.deleteById(id);
        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_MONAN_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_MONAN_NOT_FOUND);
            }
        }catch(Exception ex){
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_MONAN_FAIL);
        }
    }

    @Override
    public ViewDetailMonAnDTO getMonAnById(Long id) throws DataNotFoundException {
        ViewDetailMonAnDTO viewMonAnDTO;
        try{
            Optional<MonAn> optMonAn= monAnRepository.findById(id);
            if(!optMonAn.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_MONAN_NOT_FOUND);
            }
            MonAn monAn= optMonAn.get();
            viewMonAnDTO= monAnConverter.convertToDTO(monAn);

        }catch (Exception exception){
            throw new DataNotFoundException(ErrorCode.ERR_MONAN_NOT_FOUND);
        }
        return viewMonAnDTO;
    }

    @Override
    public List<ViewDetailMonAnDTO> getListMonAn() throws DataNotFoundException {
        List<ViewDetailMonAnDTO> listDTO;
        try{
            List<MonAn> listEntity= monAnRepository.findAll();
            listDTO=monAnConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_MONAN_LIST_LOADED_FAIL);
        }
        return listDTO;
    }

    @Override
    public List<ViewDetailMonAnDTO> getListMonAnTheoThucDon(List<Long> mama) throws DataNotFoundException {
        List<ViewDetailMonAnDTO> listDTO;
        try{
            List<MonAn> listEntity= monAnRepository.timMonAnTheoTD(mama);
            listDTO=monAnConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_MONAN_LIST_LOADED_FAIL);
        }
        return listDTO;
    }

    @Override
    public List<ViewDetailMonAnDTO> getListMonAnKhongTrung() throws DataNotFoundException {
        List<ViewDetailMonAnDTO> listDTO;
        try{
            List<MonAn> listEntity= monAnRepository.getMonAnKhongTrung();
            listDTO=monAnConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_MONAN_LIST_LOADED_FAIL);
        }
        return listDTO;
    }

    @Override
    public List<Long> getListMaNguyenLieu(String tenMonAn) throws DataNotFoundException {
        List<Long> listMaNguyenLieu;
        try{
            listMaNguyenLieu= monAnRepository.timMaNguyenLieu(tenMonAn);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_NGUYENLIEU_LIST_LOADED_FAIL);
        }
        return listMaNguyenLieu;
    }

    @Override
    public List<Long> getListMaMonAn(String tenMonAn) throws DataNotFoundException {
        List<Long> listMaNguyenLieu;
        try{
            listMaNguyenLieu= monAnRepository.getListMaMonAn(tenMonAn);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_MONAN_LIST_LOADED_FAIL);
        }
        return listMaNguyenLieu;
    }

    @Override
    public List<ViewDetailMonAnDTO> getListMAtheoLoaiMA(String loaimonan) throws DataNotFoundException {
        List<ViewDetailMonAnDTO> listDTO;
        try{
            List<MonAn> listEntity= monAnRepository.timMonAnTheoLoaiMonAn(loaimonan);
            listDTO=monAnConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_MONAN_LIST_LOADED_FAIL);
        }
        return listDTO;
    }
}
