package com.example.backend_qlnh.service.impl;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.converter.KhachHangConverter;
import com.example.backend_qlnh.dto.KhachHangDTO.CreateKhachHangDTO;
import com.example.backend_qlnh.dto.KhachHangDTO.ViewKhachHangDTO;
import com.example.backend_qlnh.entity.*;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.repository.HinhAnhRepository;
import com.example.backend_qlnh.repository.KhachHangRepository;
import com.example.backend_qlnh.repository.UserRepository;
import com.example.backend_qlnh.service.KhachHangService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KhachHangServiceImpl implements KhachHangService {
    @Autowired
    KhachHangRepository khachHangRepository;
    @Autowired
    HinhAnhRepository hinhAnhRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    KhachHangConverter khachHangConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(KhachHangServiceImpl.class);
    @Override
    public KhachHang createKhachHang(CreateKhachHangDTO createKhachHangDTO) throws CreateDataFailException {
        KhachHang khachHang;
        try{
            khachHang=khachHangConverter.convertToEntity(createKhachHangDTO);
//            khachHang.setVangLai(false);//set vang lai false
            return khachHangRepository.save(khachHang);
        }catch (Exception ex){
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_KHACHHANG_FAIL);
        }
    }

    @Override
    public KhachHang updateKhachHang(ViewKhachHangDTO viewDetailKhachHangDTO, Long id) throws UpdateDataFailException, DataNotFoundException {
        try{
            Optional<KhachHang> opt= khachHangRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("KhachHang khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            }
            KhachHang khachHang= opt.get();
            khachHang.setTenKhachHang(viewDetailKhachHangDTO.getTenKhachHang());
            khachHang.setSdt(viewDetailKhachHangDTO.getSdt());
            khachHang.setNgaySinh(viewDetailKhachHangDTO.getNgaySinh());
            khachHang.setGioiTinh(viewDetailKhachHangDTO.isGioiTinh());
            khachHang.setEmail(viewDetailKhachHangDTO.getEmail());
            khachHang.setDiemTichLuy(viewDetailKhachHangDTO.getDiemTichLuy());


            if(viewDetailKhachHangDTO.getMaHinhAnh()==null){
               khachHang.setHinhAnhKH(null);
            }else{
                Optional<HinhAnh> optHinhAnh= hinhAnhRepository.findById(viewDetailKhachHangDTO.getMaHinhAnh());
                HinhAnh hinhAnh= optHinhAnh.get();
                khachHang.setHinhAnhKH(hinhAnh);
            }


            Optional<User> optTaiKhoan= userRepository.findById(viewDetailKhachHangDTO.getMaTaiKhoan());
            if(!optTaiKhoan.isPresent()){
                LOGGER.info("TaiKhoan khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_TAIKHOAN_NOT_FOUND);
            }
            User user = optTaiKhoan.get();
            khachHang.setTaiKhoanKH(user);

            return khachHangRepository.save(khachHang);
        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_TAIKHOAN_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_TAIKHOAN_NOT_FOUND);
            }else if(message.equals(ErrorCode.ERR_HINHANH_NOT_FOUND)){
                throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
            }else{
                throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            }

        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_KHACHHANG_FAIL);
        }
    }

    @Override
    public void deleteKhachHang(Long id) throws DeleteDataFailException, DataNotFoundException {
        try{
            Optional<KhachHang> opt= khachHangRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("KhachHang khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            }
            khachHangRepository.deleteById(id);
        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_KHACHHANG_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            }
        }catch(Exception ex){
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_KHACHHANG_FAIL);
        }
    }

    @Override
    public ViewKhachHangDTO getKhachHangById(Long id) throws DataNotFoundException {
        ViewKhachHangDTO viewKhachHangDTO;
        try{
            Optional<KhachHang> optKhachHang= khachHangRepository.findById(id);
            if(!optKhachHang.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            }
            KhachHang khachHang= optKhachHang.get();
            viewKhachHangDTO= khachHangConverter.convertToDTO(khachHang);

        }catch (Exception exception){
            throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
        }
        return viewKhachHangDTO;
    }

    @Override
    public List<ViewKhachHangDTO> getListKhachHang() throws DataNotFoundException {
        List<ViewKhachHangDTO> listDTO;
        try{
            List<KhachHang> listEntity= khachHangRepository.findAll();
            listDTO=khachHangConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_LIST_LOADED_FAIL);
        }
        return listDTO;
    }

    @Override
    public ViewKhachHangDTO getKHByMaTK(Long id) throws DataNotFoundException {
        ViewKhachHangDTO viewKhachHangDTO;
        try{
            Optional<KhachHang> optKhachHang= khachHangRepository.getKHTheoMaTK(id);
            if(!optKhachHang.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            }
            KhachHang khachHang= optKhachHang.get();
            viewKhachHangDTO= khachHangConverter.convertToDTO(khachHang);

        }catch (Exception exception){
            throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
        }
        return viewKhachHangDTO;
    }

    @Override
    public Boolean getKHBySdt(String sdt) throws DataNotFoundException {
        ViewKhachHangDTO viewKhachHangDTO;
        try{
            Optional<KhachHang> optKhachHang= khachHangRepository.getKHTheoSdt(sdt);
            if(!optKhachHang.isPresent()){
                return false;
            }
        }catch (Exception exception){
            throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
        }
        return true;
    }
}
