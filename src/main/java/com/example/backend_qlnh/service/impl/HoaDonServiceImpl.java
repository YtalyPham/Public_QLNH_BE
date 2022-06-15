package com.example.backend_qlnh.service.impl;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.converter.HoaDonConverter;
import com.example.backend_qlnh.dto.HoaDonDTO.CreateHoaDonDTO;
import com.example.backend_qlnh.dto.HoaDonDTO.ViewHoaDonDTO;
import com.example.backend_qlnh.dto.PhieuDatTiecDTO.ViewPhieuDatTiecDTO;
import com.example.backend_qlnh.entity.*;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.repository.HoaDonRepository;
import com.example.backend_qlnh.repository.KhachHangRepository;
import com.example.backend_qlnh.repository.NhaHangRepository;
import com.example.backend_qlnh.repository.NhanVienRepository;
import com.example.backend_qlnh.service.HoaDonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HoaDonServiceImpl implements HoaDonService {
    @Autowired
    HoaDonRepository hoaDonRepository;
    @Autowired
    KhachHangRepository khachHangRepository;
    @Autowired
    NhanVienRepository nhanVienRepository;
    @Autowired
    NhaHangRepository nhaHangRepository;
    @Autowired
    HoaDonConverter hoaDonConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(HoaDonServiceImpl.class);

    @Override
    public HoaDon createHoaDon(CreateHoaDonDTO createHoaDonDTO) throws CreateDataFailException {
        HoaDon hoaDon;
        try{
            hoaDon=hoaDonConverter.convertToEntity(createHoaDonDTO);
            return hoaDonRepository.save(hoaDon);
        }catch (Exception ex){
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_HOADON_FAIL);
        }
    }

    @Override
    public HoaDon updateHoaDon(ViewHoaDonDTO viewDetailHoaDonDTO, Long id) throws UpdateDataFailException, DataNotFoundException {
        try{
            Optional<HoaDon> opt= hoaDonRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("HoaDon khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_HOADON_NOT_FOUND);
            }
            HoaDon hoaDon= opt.get();
            hoaDon.setNgayLapHoaDon(viewDetailHoaDonDTO.getNgayLapHoaDon());

            Optional<NhaHang> opt1= nhaHangRepository.findById(viewDetailHoaDonDTO.getMaNhaHang());
            if(!opt1.isPresent()){
                LOGGER.info("NhaHang khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_NHAHANG_NOT_FOUND);
            }
            NhaHang nhaHang= opt1.get();
            hoaDon.setNhaHangHD(nhaHang);

            Optional<KhachHang> opt3= khachHangRepository.findById(viewDetailHoaDonDTO.getMaKhachHang());
            if(!opt3.isPresent()){
                LOGGER.info("KhachHang khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            }
            KhachHang khachHang= opt3.get();
            hoaDon.setKhachHangHD(khachHang);



            Optional<NhanVien> opt5= nhanVienRepository.findById(viewDetailHoaDonDTO.getMaNhanVien());
            if(!opt3.isPresent()){
                LOGGER.info("NhanVien khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_NHANVIEN_NOT_FOUND);
            }
            NhanVien nhanVien= opt5.get();
            hoaDon.setNhanVienHD(nhanVien);

            return hoaDonRepository.save(hoaDon);
        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_NHANVIEN_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_NHANVIEN_NOT_FOUND);
            }else if(message.equals(ErrorCode.ERR_KHACHHANG_NOT_FOUND)){
                throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            }else if(message.equals(ErrorCode.ERR_NHAHANG_NOT_FOUND)){
                throw new DataNotFoundException(ErrorCode.ERR_NHAHANG_NOT_FOUND);
            }else{
                throw new DataNotFoundException(ErrorCode.ERR_HOADON_NOT_FOUND);
            }
        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_HOADON_FAIL);
        }
    }

    @Override
    public void deleteHoaDon(Long id) throws DeleteDataFailException, DataNotFoundException {
        try{
            Optional<HoaDon> opt= hoaDonRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("HoaDon khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_HOADON_NOT_FOUND);
            }
            hoaDonRepository.deleteById(id);
        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_HOADON_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_HOADON_NOT_FOUND);
            }
        }catch(Exception ex){
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_HOADON_FAIL);
        }
    }

    @Override
    public ViewHoaDonDTO getHoaDonById(Long id) throws DataNotFoundException {
        ViewHoaDonDTO viewHoaDonDTO;
        try{
            Optional<HoaDon> optHoaDon= hoaDonRepository.findById(id);
            if(!optHoaDon.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_HOADON_NOT_FOUND);
            }
            HoaDon hoaDon= optHoaDon.get();
            viewHoaDonDTO= hoaDonConverter.convertToDTO(hoaDon);

        }catch (Exception exception){
            throw new DataNotFoundException(ErrorCode.ERR_HOADON_NOT_FOUND);
        }
        return viewHoaDonDTO;
    }

    @Override
    public List<ViewHoaDonDTO> getListHoaDon() throws DataNotFoundException {
        List<ViewHoaDonDTO> listDTO;
        try{
            List<HoaDon> listEntity= hoaDonRepository.findAll();
            listDTO=hoaDonConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_HOADON_LIST_LOADED_FAIL);
        }
        return listDTO;
    }
}
