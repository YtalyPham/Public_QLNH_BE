package com.example.backend_qlnh.service.impl;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.converter.ChiTietHoaDonConverter;
import com.example.backend_qlnh.dto.CTHDDTO.CreateChiTietHoaDonDTO;
import com.example.backend_qlnh.dto.CTHDDTO.ViewChiTietHoaDonDTO;
import com.example.backend_qlnh.dto.DichVuDTO.ViewDichVuDTO;
import com.example.backend_qlnh.dto.HoaDonDTO.ViewHoaDonDTO;
import com.example.backend_qlnh.entity.*;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.repository.ChiTietHoaDonRepository;
import com.example.backend_qlnh.repository.HoaDonRepository;
import com.example.backend_qlnh.repository.KhuyenMaiRepository;
import com.example.backend_qlnh.repository.PhieuDatTiecRepository;
import com.example.backend_qlnh.service.ChiTietHoaDonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChiTietHoaDonServiceImpl implements ChiTietHoaDonService {
    @Autowired
    ChiTietHoaDonRepository chiTietHoaDonRepository;
    @Autowired
    KhuyenMaiRepository khuyenMaiRepository;
    @Autowired
    PhieuDatTiecRepository phieuDatTiecRepository;
    @Autowired
    ChiTietHoaDonConverter chiTietHoaDonConverter;
    @Autowired
    HoaDonRepository hoaDonRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ChiTietHoaDonServiceImpl.class);
    
    @Override
    public ChiTietHoaDon createChiTietHoaDon(CreateChiTietHoaDonDTO createChiTietHoaDonDTO) throws CreateDataFailException {
        ChiTietHoaDon chiTietHoaDon;
        try{
            chiTietHoaDon=chiTietHoaDonConverter.convertToEntity(createChiTietHoaDonDTO);
            return chiTietHoaDonRepository.save(chiTietHoaDon);
        }catch (Exception ex){
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_CHITIETHOADON_FAIL);
        }
    }

    @Override
    public ChiTietHoaDon updateChiTietHoaDon(ViewChiTietHoaDonDTO viewDetailChiTietHoaDonDTO, Long id) throws UpdateDataFailException, DataNotFoundException {
        try{
            Optional<ChiTietHoaDon> opt= chiTietHoaDonRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("ChiTietHoaDon khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_CHITIETHOADON_NOT_FOUND);
            }
            ChiTietHoaDon chiTietHoaDon= opt.get();
            chiTietHoaDon.setTongTien(viewDetailChiTietHoaDonDTO.getTongTien());
            chiTietHoaDon.setThanhTien(viewDetailChiTietHoaDonDTO.getThanhTien());
            chiTietHoaDon.setTienThoi(viewDetailChiTietHoaDonDTO.getTienThoi());
            chiTietHoaDon.setTienKhachDua(viewDetailChiTietHoaDonDTO.getTienKhachDua());
            chiTietHoaDon.setVAT(viewDetailChiTietHoaDonDTO.getVAT());
            chiTietHoaDon.setThanhToan(viewDetailChiTietHoaDonDTO.getThanhToan());
            chiTietHoaDon.setNgayLapHoaDon(viewDetailChiTietHoaDonDTO.getNgayLapHoaDon());
            chiTietHoaDon.setTienCoc(viewDetailChiTietHoaDonDTO.getTienCoc());
            chiTietHoaDon.setTienPhatSinh(viewDetailChiTietHoaDonDTO.getTienPhatSinh());

            Optional<HoaDon> opt2= hoaDonRepository.findById(viewDetailChiTietHoaDonDTO.getMaHoaDon());
            HoaDon hoaDon;
            if(!opt.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_HOADON_NOT_FOUND);
            }
            hoaDon= opt2.get();
            chiTietHoaDon.setHoaDonCTHD(hoaDon);


            Optional<KhuyenMai> opt1= khuyenMaiRepository.findById(viewDetailChiTietHoaDonDTO.getMaKhuyenMai());
            if(!opt1.isPresent()){
                LOGGER.info("KhuyenMai khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_KHUYENMAI_NOT_FOUND);
            }
            KhuyenMai khuyenMai= opt1.get();
            chiTietHoaDon.setKhuyenMaiCTHD(khuyenMai);

            Optional<PhieuDatTiec> opt3= phieuDatTiecRepository.findById(viewDetailChiTietHoaDonDTO.getMaPhieuDatTiec());
            if(!opt3.isPresent()){
                LOGGER.info("PhieuDatTiec khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
            }
            PhieuDatTiec phieuDatTiec= opt3.get();
            chiTietHoaDon.setPhieuDatTiecCTHD(phieuDatTiec);

            return chiTietHoaDonRepository.save(chiTietHoaDon);
        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_KHUYENMAI_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_KHUYENMAI_NOT_FOUND);
            }else if(message.equals(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND)){
                throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
            }else if(message.equals(ErrorCode.ERR_HOADON_NOT_FOUND)){
                throw new DataNotFoundException(ErrorCode.ERR_HOADON_NOT_FOUND);
            }else{
                throw new DataNotFoundException(ErrorCode.ERR_CHITIETHOADON_NOT_FOUND);
            }
        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_CHITIETHOADON_FAIL);
        }
    }

    @Override
    public void deleteChiTietHoaDon(Long id) throws DeleteDataFailException, DataNotFoundException {
        try{
            Optional<ChiTietHoaDon> opt= chiTietHoaDonRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("ChiTietHoaDon khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_CHITIETHOADON_NOT_FOUND);
            }
            chiTietHoaDonRepository.deleteById(id);
        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_CHITIETHOADON_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_CHITIETHOADON_NOT_FOUND);
            }
        }catch(Exception ex){
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_CHITIETHOADON_FAIL);
        }
    }

    @Override
    public ViewChiTietHoaDonDTO getChiTietHoaDonById(Long id) throws DataNotFoundException {
        ViewChiTietHoaDonDTO viewChiTietHoaDonDTO;
        try{
            Optional<ChiTietHoaDon> optChiTietHoaDon= chiTietHoaDonRepository.findById(id);
            if(!optChiTietHoaDon.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_CHITIETHOADON_NOT_FOUND);
            }
            ChiTietHoaDon chiTietHoaDon= optChiTietHoaDon.get();
            viewChiTietHoaDonDTO= chiTietHoaDonConverter.convertToDTO(chiTietHoaDon);

        }catch (Exception exception){
            throw new DataNotFoundException(ErrorCode.ERR_CHITIETHOADON_NOT_FOUND);
        }
        return viewChiTietHoaDonDTO;
    }

    @Override
    public List<ViewChiTietHoaDonDTO> getListChiTietHoaDon() throws DataNotFoundException {
        List<ViewChiTietHoaDonDTO> listDTO;
        try{
            List<ChiTietHoaDon> listEntity= chiTietHoaDonRepository.findAll();
            listDTO=chiTietHoaDonConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_CHITIETHOADON_LIST_LOADED_FAIL);
        }
        return listDTO;
    }

    @Override
    public ViewChiTietHoaDonDTO getCthdByIdPDT(Long id) throws DataNotFoundException {
        ViewChiTietHoaDonDTO viewChiTietHoaDonDTO;
        try{
            Optional<ChiTietHoaDon> optChiTietHoaDon= chiTietHoaDonRepository.timCTHDtheoPDT(id);
            if(!optChiTietHoaDon.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_CHITIETHOADON_NOT_FOUND);
            }
            ChiTietHoaDon chiTietHoaDon= optChiTietHoaDon.get();
            viewChiTietHoaDonDTO= chiTietHoaDonConverter.convertToDTO(chiTietHoaDon);

        }catch (Exception exception){
            throw new DataNotFoundException(ErrorCode.ERR_CHITIETHOADON_NOT_FOUND);
        }
        return viewChiTietHoaDonDTO;
    }



    @Override
    public List<Long> getCthdTheoNgay(String ngaylaphoadon) throws DataNotFoundException {
        List<Long> listCthd;
        try{
            listCthd= chiTietHoaDonRepository.timCTHDtheoNgay(ngaylaphoadon);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_CHITIETHOADON_LIST_LOADED_FAIL);
        }
        return listCthd;
    }

    @Override
    public List<Long> getCthdTheoThang(String ngaylaphoadon) throws DataNotFoundException {
        List<Long> listCthd;
        try{
            listCthd= chiTietHoaDonRepository.timCTHDtheoThang(ngaylaphoadon);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_CHITIETHOADON_LIST_LOADED_FAIL);
        }
        return listCthd;
    }

    @Override
    public List<Long> getCthdTheoNam(String ngaylaphoadon) throws DataNotFoundException {
        List<Long> listCthd;
        try{
            listCthd= chiTietHoaDonRepository.timCTHDtheoNam(ngaylaphoadon);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_CHITIETHOADON_LIST_LOADED_FAIL);
        }
        return listCthd;
    }

    @Override
    public List<Long> getCthdTheoKhoang(String ngaydau, String ngaysau) throws DataNotFoundException {
        List<Long> listCthd;
        try{
            listCthd= chiTietHoaDonRepository.timCTHDtheoKhoang(ngaydau,ngaysau);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_CHITIETHOADON_LIST_LOADED_FAIL);
        }
        return listCthd;
    }

    @Override
    public List<ViewChiTietHoaDonDTO> getListCthdTheoListId(List<Long> macthd) throws DataNotFoundException {
        List<ViewChiTietHoaDonDTO> listDTO;
        try {
            List<ChiTietHoaDon> listEntity = chiTietHoaDonRepository.timCTHDtheoListId(macthd);
            listDTO = chiTietHoaDonConverter.convertToListDTO(listEntity);

        } catch (Exception exception) {
            throw new DataNotFoundException(ErrorCode.ERR_CHITIETHOADON_NOT_FOUND);
        }
        return listDTO;
    }


}
