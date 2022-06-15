package com.example.backend_qlnh.service.impl;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.converter.PhieuDatTiecConverter;
import com.example.backend_qlnh.dto.NhanVienDTO.ViewNhanVienDTO;
import com.example.backend_qlnh.dto.PhieuDatTiecDTO.CreatePhieuDatTiecDTO;
import com.example.backend_qlnh.dto.PhieuDatTiecDTO.ViewPhieuDatTiecDTO;
import com.example.backend_qlnh.entity.*;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.repository.*;
import com.example.backend_qlnh.service.PhieuDatTiecService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class PhieuDatTiecServiceImpl implements PhieuDatTiecService {
    @Autowired
    PhieuDatTiecRepository phieuDatTiecRepository;
    @Autowired
    TapDichVuRepository tapDichVuRepository;
    @Autowired
    ThucDonRepository thucDonRepository;
    @Autowired
    LoaiHinhSuKienRepository loaiHinhSuKienRepository;
    @Autowired
    SanhTiecRepository sanhTiecRepository;
    @Autowired
    KhachHangRepository khachHangRepository;
    @Autowired
    PhieuDatTiecConverter phieuDatTiecConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(PhieuDatTiecServiceImpl.class);


    @Override
    public PhieuDatTiec createPhieuDatTiec(CreatePhieuDatTiecDTO createPhieuDatTiecDTO) throws CreateDataFailException {
        PhieuDatTiec phieuDatTiec;
        try{
            phieuDatTiec=phieuDatTiecConverter.convertToEntity(createPhieuDatTiecDTO);
            return phieuDatTiecRepository.save(phieuDatTiec);
        }catch (Exception ex){
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_PHIEUDATTIEC_FAIL);
        }
    }

    @Override
    public PhieuDatTiec updatePhieuDatTiec(ViewPhieuDatTiecDTO viewDetailPhieuDatTiecDTO, Long id) throws UpdateDataFailException, DataNotFoundException {
        try{
            Optional<PhieuDatTiec> opt= phieuDatTiecRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("PhieuDatTiec khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
            }
            PhieuDatTiec phieuDatTiec= opt.get();
            phieuDatTiec.setNgayToChuc(viewDetailPhieuDatTiecDTO.getNgayToChuc());
            phieuDatTiec.setSoLuongBan(viewDetailPhieuDatTiecDTO.getSoLuongBan());
            phieuDatTiec.setGhiChu(viewDetailPhieuDatTiecDTO.getGhiChu());
            phieuDatTiec.setLichHen(viewDetailPhieuDatTiecDTO.getLichHen());
            phieuDatTiec.setBuoi(viewDetailPhieuDatTiecDTO.getBuoi());
            phieuDatTiec.setThanhToan(viewDetailPhieuDatTiecDTO.isThanhToan());
            phieuDatTiec.setThanhTien(viewDetailPhieuDatTiecDTO.getThanhTien());
            phieuDatTiec.setTienCoc(viewDetailPhieuDatTiecDTO.getTienCoc());
            phieuDatTiec.setNgayDatTiec(viewDetailPhieuDatTiecDTO.getNgayDatTiec());

            Optional<TapDichVu> opt1= tapDichVuRepository.findById(viewDetailPhieuDatTiecDTO.getMaTapDichVu());
            if(!opt1.isPresent()){
                LOGGER.info("Tap dich vu khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_DICHVU_NOT_FOUND);
            }
            TapDichVu tapDichVu= opt1.get();
            phieuDatTiec.setTapDichVuPTD(tapDichVu);

            Optional<SanhTiec> opt2= sanhTiecRepository.findById(viewDetailPhieuDatTiecDTO.getMaSanhTiec());
            if(!opt2.isPresent()){
                LOGGER.info("SanhTiec khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_SANHTIEC_NOT_FOUND);
            }
            SanhTiec sanhTiec= opt2.get();
            phieuDatTiec.setSanhTiecPDT(sanhTiec);

            Optional<KhachHang> opt3= khachHangRepository.findById(viewDetailPhieuDatTiecDTO.getMaKhachHang());
            if(!opt3.isPresent()){
                LOGGER.info("KhachHang khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            }
            KhachHang khachHang= opt3.get();
            phieuDatTiec.setKhachHangPDT(khachHang);

            Optional<ThucDon> opt4= thucDonRepository.findById(viewDetailPhieuDatTiecDTO.getMaThucDon());
            if(!opt4.isPresent()){
                LOGGER.info("ThucDon khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_THUCDON_NOT_FOUND);
            }
            ThucDon thucDon= opt4.get();
            phieuDatTiec.setThucDonPDT(thucDon);

            Optional<LoaiHinhSuKien> opt5= loaiHinhSuKienRepository.findById(viewDetailPhieuDatTiecDTO.getMaLoaiHinhSuKien());
            if(!opt3.isPresent()){
                LOGGER.info("LoaiHinhSuKien khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_LOAIHINHSUKIEN_NOT_FOUND);
            }
            LoaiHinhSuKien loaiHinhSuKien= opt5.get();
            phieuDatTiec.setLoaiHinhSuKienPDT(loaiHinhSuKien);

            return phieuDatTiecRepository.save(phieuDatTiec);
        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_SANHTIEC_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_SANHTIEC_NOT_FOUND);
            }else if(message.equals(ErrorCode.ERR_KHACHHANG_NOT_FOUND)){
                throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            }else if(message.equals(ErrorCode.ERR_LOAIHINHSUKIEN_NOT_FOUND)){
                throw new DataNotFoundException(ErrorCode.ERR_LOAIHINHSUKIEN_NOT_FOUND);
            }else if(message.equals(ErrorCode.ERR_THUCDON_NOT_FOUND)){
                throw new DataNotFoundException(ErrorCode.ERR_THUCDON_NOT_FOUND);
            }else if(message.equals(ErrorCode.ERR_TAPDICHVU_NOT_FOUND)){
                throw new DataNotFoundException(ErrorCode.ERR_TAPDICHVU_NOT_FOUND);
            }else{
                throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
            }

        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_PHIEUDATTIEC_FAIL);
        }
    }

    @Override
    public void deletePhieuDatTiec(Long id) throws DeleteDataFailException, DataNotFoundException {
        try{
            Optional<PhieuDatTiec> opt= phieuDatTiecRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("PhieuDatTiec khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
            }
            phieuDatTiecRepository.deleteById(id);
        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
            }
        }catch(Exception ex){
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_PHIEUDATTIEC_FAIL);
        }
    }

    @Override
    public ViewPhieuDatTiecDTO getPhieuDatTiecById(Long id) throws DataNotFoundException {
        ViewPhieuDatTiecDTO viewPhieuDatTiecDTO;
        try{
            Optional<PhieuDatTiec> optPhieuDatTiec= phieuDatTiecRepository.findById(id);
            if(!optPhieuDatTiec.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
            }
            PhieuDatTiec phieuDatTiec= optPhieuDatTiec.get();
            viewPhieuDatTiecDTO= phieuDatTiecConverter.convertToDTO(phieuDatTiec);

        }catch (Exception exception){
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
        }
        return viewPhieuDatTiecDTO;
    }

    @Override
    public List<ViewPhieuDatTiecDTO> getListPhieuDatTiec() throws DataNotFoundException {
        List<ViewPhieuDatTiecDTO> listDTO;
        try{
            List<PhieuDatTiec> listEntity= phieuDatTiecRepository.findAll();
            listDTO=phieuDatTiecConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
        }
        return listDTO;
    }

    @Override
    public List<ViewPhieuDatTiecDTO> getListPhieuDatTiecTheoMaKH(Long makh) throws DataNotFoundException {
        List<ViewPhieuDatTiecDTO> listDTO;
        try{
            List<PhieuDatTiec> listEntity= phieuDatTiecRepository.timPDTTheoMaKH(makh);
            listDTO=phieuDatTiecConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
        }
        return listDTO;
    }

    @Override
    public List<ViewPhieuDatTiecDTO> getListPDTDaThanhToanTheoMaKH(Long makh) throws DataNotFoundException {
        List<ViewPhieuDatTiecDTO> listDTO;
        try{
            List<PhieuDatTiec> listEntity= phieuDatTiecRepository.timPDTDaTTTheoMaKH(makh);
            listDTO=phieuDatTiecConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
        }
        return listDTO;
    }

    @Override
    public List<ViewPhieuDatTiecDTO> getListPDTChuaThanhToanTheoMaKH(Long makh) throws DataNotFoundException {
        List<ViewPhieuDatTiecDTO> listDTO;
        try{
            List<PhieuDatTiec> listEntity= phieuDatTiecRepository.timPDTChuaTTTheoMaKH(makh);
            listDTO=phieuDatTiecConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
        }
        return listDTO;
    }

    @Override
    public List<ViewPhieuDatTiecDTO> getListPhieuDatTiecTheoMakhNgay(Long makh, LocalDateTime ngayDat) throws DataNotFoundException {
        List<ViewPhieuDatTiecDTO> listDTO;
//        List<PhieuDatTiec> listEntity;
        try{
            List<PhieuDatTiec> listEntity= phieuDatTiecRepository.timMaKHAndNgayDatTiec(makh,ngayDat);
           listDTO=phieuDatTiecConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
        }
        return listDTO;
    }

    @Override
    public List<ViewPhieuDatTiecDTO> getListPhieuDatTiecTheoMakhNgayBuoi(Long makh,LocalDateTime ngayDat, String buoi) throws DataNotFoundException {
        List<ViewPhieuDatTiecDTO> listDTO;
//        List<PhieuDatTiec> listEntity;
        try{
            List<PhieuDatTiec> listEntity= phieuDatTiecRepository.timMaKHAndNgayDatTiecAndBuoi(makh,ngayDat,buoi);
            listDTO=phieuDatTiecConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
        }
        return listDTO;
    }

    @Override
    public List<Long> getListMaSanhTiecTheoNgayBuoi(LocalDateTime ngayDat, String buoi) throws DataNotFoundException {
        List<Long> listMaDatTiec;
        try{
            listMaDatTiec= phieuDatTiecRepository.timMaSTTheoNgayDatTiecAndBuoi(ngayDat,buoi);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
        }
        return listMaDatTiec;
    }

    @Override
    public Integer getPDTchuaTT(Long mkh) throws DataNotFoundException {
        Integer sl;
        try{
            sl= phieuDatTiecRepository.timPDTchuaTT(mkh);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
        }
        return sl;
    }

    @Override
    public List<ViewPhieuDatTiecDTO> getListPhieuDatTiecDaTT() throws DataNotFoundException {
        List<ViewPhieuDatTiecDTO> listDTO;
        try{
            List<PhieuDatTiec> listEntity= phieuDatTiecRepository.timPDTDaThanhToan();
            listDTO=phieuDatTiecConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
        }
        return listDTO;
    }

    @Override
    public List<ViewPhieuDatTiecDTO> getListPhieuDatTiecChuaTT() throws DataNotFoundException {
        List<ViewPhieuDatTiecDTO> listDTO;
        try{
            List<PhieuDatTiec> listEntity= phieuDatTiecRepository.timPDTChuaThanhToan();
            listDTO=phieuDatTiecConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
        }
        return listDTO;
    }

    @Override
    public PhieuDatTiec updateLichHenPhieuDatTiec(LocalDateTime lichHen, Long id) throws UpdateDataFailException {
        try{
            Optional<PhieuDatTiec> opt= phieuDatTiecRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("PhieuDatTiec khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
            }
            PhieuDatTiec phieuDatTiec= opt.get();

            phieuDatTiec.setLichHen(lichHen);


            return phieuDatTiecRepository.save(phieuDatTiec);


        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_PHIEUDATTIEC_FAIL);
        }
    }

    @Override
    public PhieuDatTiec updateThanhToanPhieuDatTiec(Long id) throws UpdateDataFailException {
        try{
            Optional<PhieuDatTiec> opt= phieuDatTiecRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("PhieuDatTiec khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
            }
            PhieuDatTiec phieuDatTiec= opt.get();

            phieuDatTiec.setThanhToan(true);


            return phieuDatTiecRepository.save(phieuDatTiec);


        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_PHIEUDATTIEC_FAIL);
        }
    }

    @Override
    public PhieuDatTiec updateTienCocPhieuDatTiec(Double tiencoc, Long id) throws UpdateDataFailException {
        try{
            Optional<PhieuDatTiec> opt= phieuDatTiecRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("PhieuDatTiec khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
            }
            PhieuDatTiec phieuDatTiec= opt.get();

            phieuDatTiec.setTienCoc(tiencoc);


            return phieuDatTiecRepository.save(phieuDatTiec);


        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_PHIEUDATTIEC_FAIL);
        }
    }

    @Override
    public List<Long> getPDTTheoNgayTC(String ngaytochuc) throws DataNotFoundException {
        List<Long> listCthd;
        try{
            listCthd= phieuDatTiecRepository.timPDTtheoNgayTC(ngaytochuc);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_CHITIETHOADON_LIST_LOADED_FAIL);
        }
        return listCthd;
    }

    @Override
    public List<ViewPhieuDatTiecDTO> getListPhieuDatTiecTheoListMaPDT(List<Long> malistid) throws DataNotFoundException {
        List<ViewPhieuDatTiecDTO> listDTO;
        try{
            List<PhieuDatTiec> listEntity= phieuDatTiecRepository.getPDTTheoListMaPDT(malistid);
            listDTO=phieuDatTiecConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
        }
        return listDTO;
    }

    @Override
    public Integer demslPDTchuaTT() throws DataNotFoundException {
        Integer sl;
        try{
            sl= phieuDatTiecRepository.demPDTchuaTT();
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
        }
        return sl;
    }

    @Override
    public Integer demslPDTdaTT() throws DataNotFoundException {
        Integer sl;
        try{
            sl= phieuDatTiecRepository.demPDTdaTT();
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
        }
        return sl;
    }

    @Override
    public Integer demslPDTtochuchomnay(String ngay) throws DataNotFoundException {
        Integer sl;
        try{
            sl= phieuDatTiecRepository.demPDTtrongHomNay(ngay);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
        }
        return sl;
    }

//    @Override
//    public List<Long> getListMaDichVuTheoMaKhNgayBuoi(Long makh, LocalDateTime ngayDat, String buoi) throws DataNotFoundException {
//        List<Long> listMaDatTiec;
//        try{
//            listMaDatTiec= phieuDatTiecRepository.timMaDVTheoMaKHAndNgayDatTiecAndBuoi(makh,ngayDat,buoi);
//        }catch (Exception ex){
//            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
//        }
//        return listMaDatTiec;
//    }
}
