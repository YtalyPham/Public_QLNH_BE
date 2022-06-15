package com.example.backend_qlnh.service.impl;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.converter.PhieuHenConverter;
import com.example.backend_qlnh.dto.KhachHangDTO.ViewKhachHangDTO;
import com.example.backend_qlnh.dto.PhieuHenDTO.CreatePhieuHenDTO;
import com.example.backend_qlnh.dto.PhieuHenDTO.ViewPhieuHenDTO;
import com.example.backend_qlnh.entity.*;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.repository.KhachHangRepository;
import com.example.backend_qlnh.repository.LoaiHinhSuKienRepository;
import com.example.backend_qlnh.repository.PhieuHenRepository;
import com.example.backend_qlnh.service.PhieuHenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhieuHenServiceImpl implements PhieuHenService {
    @Autowired
    PhieuHenRepository phieuHenRepository;
    @Autowired
    KhachHangRepository khachHangRepository;
    @Autowired
    LoaiHinhSuKienRepository loaiHinhSuKienRepository;
    @Autowired
    PhieuHenConverter phieuHenConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(PhieuHenServiceImpl.class);
    @Override
    public PhieuHen createPhieuHen(CreatePhieuHenDTO createPhieuHenDTO) throws CreateDataFailException {
        PhieuHen phieuHen;
        try{
            phieuHen=phieuHenConverter.convertToEntity(createPhieuHenDTO);
            return phieuHenRepository.save(phieuHen);
        }catch (Exception ex){
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_PHIEUHEN_FAIL);
        }
    }

    @Override
    public PhieuHen updatePhieuHen(ViewPhieuHenDTO viewDetailPhieuHenDTO, Long id) throws UpdateDataFailException, DataNotFoundException {
        try{
            Optional<PhieuHen> opt= phieuHenRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("PhieuHen khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_PHIEUHEN_NOT_FOUND);
            }
            PhieuHen phieuHen= opt.get();
            phieuHen.setLichHen(viewDetailPhieuHenDTO.getLichHen());
            phieuHen.setGhiChu(viewDetailPhieuHenDTO.getGhiChu());
            phieuHen.setNgayDatTiec(viewDetailPhieuHenDTO.getNgayDatTiec());
            phieuHen.setSoLuongBan(viewDetailPhieuHenDTO.getSoLuongBan());
            phieuHen.setBuoi(viewDetailPhieuHenDTO.getBuoi());

            Optional<KhachHang> optKH= khachHangRepository.findById(viewDetailPhieuHenDTO.getMaKhachHang());
            if(!optKH.isPresent()){
                LOGGER.info("KhachHang khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            }
            KhachHang khachHang= optKH.get();
            phieuHen.setKhachHangPH(khachHang);

            Optional<LoaiHinhSuKien> optLHSK= loaiHinhSuKienRepository.findById(viewDetailPhieuHenDTO.getMaLoaiHinhSuKien());
            if(!optLHSK.isPresent()){
                LOGGER.info("LoaiHinhSuKien khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_LOAIHINHSUKIEN_NOT_FOUND);
            }
            LoaiHinhSuKien loaiHinhSuKien= optLHSK.get();
            phieuHen.setLoaiHinhSuKienPH(loaiHinhSuKien);

            return phieuHenRepository.save(phieuHen);
        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_KHACHHANG_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            }else if(message.equals(ErrorCode.ERR_LOAIHINHSUKIEN_NOT_FOUND)){
                throw new DataNotFoundException(ErrorCode.ERR_LOAIHINHSUKIEN_NOT_FOUND);
            }else{
                throw new DataNotFoundException(ErrorCode.ERR_PHIEUHEN_NOT_FOUND);
            }

        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_PHIEUHEN_FAIL);
        }
    }

    @Override
    public void deletePhieuHen(Long id) throws DeleteDataFailException, DataNotFoundException {
        try{
            Optional<PhieuHen> opt= phieuHenRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("PhieuHen khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_PHIEUHEN_NOT_FOUND);
            }
            phieuHenRepository.deleteById(id);
        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_PHIEUHEN_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_PHIEUHEN_NOT_FOUND);
            }
        }catch(Exception ex){
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_PHIEUHEN_FAIL);
        }
    }

    @Override
    public ViewPhieuHenDTO getPhieuHenById(Long id) throws DataNotFoundException {
        ViewPhieuHenDTO viewPhieuHenDTO;
        try{
            Optional<PhieuHen> optPhieuHen= phieuHenRepository.findById(id);
            if(!optPhieuHen.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_PHIEUHEN_NOT_FOUND);
            }
            PhieuHen phieuHen= optPhieuHen.get();
            viewPhieuHenDTO= phieuHenConverter.convertToDTO(phieuHen);

        }catch (Exception exception){
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUHEN_NOT_FOUND);
        }
        return viewPhieuHenDTO;
    }

    @Override
    public List<ViewPhieuHenDTO> getListPhieuHen() throws DataNotFoundException {
        List<ViewPhieuHenDTO> listDTO;
        try{
            List<PhieuHen> listEntity= phieuHenRepository.findAll();
            listDTO=phieuHenConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUHEN_LIST_LOADED_FAIL);
        }
        return listDTO;
    }
}
