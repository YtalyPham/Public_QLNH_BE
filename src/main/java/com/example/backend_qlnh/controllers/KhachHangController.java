package com.example.backend_qlnh.controllers;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.constants.SuccessCode;
import com.example.backend_qlnh.converter.KhachHangConverter;
import com.example.backend_qlnh.dto.KhachHangDTO.CreateKhachHangDTO;
import com.example.backend_qlnh.dto.KhachHangDTO.ViewKhachHangDTO;
import com.example.backend_qlnh.dto.ResponseDTO.ResponseDTO;
import com.example.backend_qlnh.entity.KhachHang;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.service.KhachHangService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/khachhang")
public class KhachHangController {
    @Autowired
    KhachHangService khachHangService;
    @Autowired
    KhachHangConverter khachHangConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(KhachHangController.class);

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> getAllKhachHang() throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<ViewKhachHangDTO> listDTO=khachHangService.getListKhachHang();
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_KHACHHANG_LIST_LOADED);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_KHACHHANG_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list KhachHang: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_KHACHHANG_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> getKhachHangById(@PathVariable Long id) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            ViewKhachHangDTO khachHangDTO= khachHangService.getKhachHangById(id);
            if(khachHangDTO!=null){
                responseDTO.setData(khachHangDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_KHACHHANG_FOUND);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list KhachHang: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/mataikhoan/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> getKhachHangByMaTK(@PathVariable String id) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        Long id1=Long.parseLong(id);
        try{
            ViewKhachHangDTO khachHangDTO= khachHangService.getKHByMaTK(id1);
            if(khachHangDTO!=null){
                responseDTO.setData(khachHangDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_KHACHHANG_FOUND);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi KhachHang by maKhachHang: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/sdt/{id}")
    public ResponseEntity<ResponseDTO> getKhachHangBySdt(@PathVariable String id) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            Boolean khachHangDTO= khachHangService.getKHBySdt(id);
            if(khachHangDTO==true){
                responseDTO.setData(true);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_KHACHHANG_FOUND);
            }else{
                responseDTO.setData(false);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_KHACHHANG_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi tìm KhachHang by sdt: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_KHACHHANG_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> createNewKhachHang(@RequestBody CreateKhachHangDTO createKhachHangDTO)
            throws CreateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            KhachHang khachHang= khachHangService.createKhachHang(createKhachHangDTO);
            ViewKhachHangDTO viewKhachHangDTO= khachHangConverter.convertToDTO(khachHang);
            responseDTO.setData(viewKhachHangDTO);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_CREATE_KHACHHANG);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi tạo KhachHang: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CREATE_KHACHHANG_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_KHACHHANG_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);

    }

    @PutMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updateKhachHang(@RequestBody ViewKhachHangDTO viewKhachHangDTO)
            throws UpdateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            KhachHang khachHang= khachHangService.updateKhachHang(viewKhachHangDTO,viewKhachHangDTO.getMaKhachHang());
            ViewKhachHangDTO dto= khachHangConverter.convertToDTO(khachHang);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_UPDATE_KHACHHANG);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật KhachHang: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_KHACHHANG_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_KHACHHANG_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> deleteKhachHang(@PathVariable Long id)
            throws DeleteDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            khachHangService.deleteKhachHang(id);
            responseDTO.setData("Xoa thanh cong KhachHang id: "+id);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_DELETE_KHACHHANG);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật KhachHang: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_DELETE_KHACHHANG_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_KHACHHANG_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }
    
    
    
    
    
}
