package com.example.backend_qlnh.controllers;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.constants.SuccessCode;
import com.example.backend_qlnh.converter.LoaiNhanVienConverter;
import com.example.backend_qlnh.dto.LoaiNhanVienDTO.CreateLoaiNhanVienDTO;
import com.example.backend_qlnh.dto.LoaiNhanVienDTO.ViewDetailLoaiNhanVien;
import com.example.backend_qlnh.dto.NguyenLieuDTO.CreateNguyenLieuDTO;
import com.example.backend_qlnh.dto.NguyenLieuDTO.ViewDetailNguyenLieuDTO;
import com.example.backend_qlnh.dto.ResponseDTO.ResponseDTO;
import com.example.backend_qlnh.entity.LoaiNhanVien;
import com.example.backend_qlnh.entity.NguyenLieu;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.service.LoaiNhanVienService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/loainhanvien")
public class LoaiNhanVienController {
    
    @Autowired
    LoaiNhanVienService loaiNhanVienService;
    @Autowired
    LoaiNhanVienConverter loaiNhanVienConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoaiNhanVienController.class);

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> getAllLoaiNhanVien() throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<ViewDetailLoaiNhanVien> listDTO=loaiNhanVienService.getListLoaiNhanVien();
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_LOAINHANVIEN_LIST_LOADED);
            }else{
                responseDTO.setData(false);
                    responseDTO.setErrorCode(ErrorCode.ERR_LOAINHANVIEN_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list loai nhan vien: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_LOAINHANVIEN_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_LOAINHANVIEN_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getLoaiNhanVienById(@PathVariable Long id) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            ViewDetailLoaiNhanVien LoaiNhanVienDTO= loaiNhanVienService.getLoaiNhanVienById(id);
            if(LoaiNhanVienDTO!=null){
                responseDTO.setData(LoaiNhanVienDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_LOAINHANVIEN_FOUND);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_LOAINHANVIEN_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list loại nhân viên: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_LOAINHANVIEN_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_LOAINHANVIEN_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> createNewLoaiNhanVien(@RequestBody CreateLoaiNhanVienDTO createLoaiNhanVienDTO)
            throws CreateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            LoaiNhanVien loaiNhanVien= loaiNhanVienService.createLoaiNhanVien(createLoaiNhanVienDTO);
            ViewDetailLoaiNhanVien viewLoaiNhanVienDTO= loaiNhanVienConverter.convertToDTO(loaiNhanVien);
            responseDTO.setData(viewLoaiNhanVienDTO);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_CREATE_LOAINHANVIEN);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi tạo loại nhân viên: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CREATE_LOAINHANVIEN_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_LOAINHANVIEN_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);

    }

    @PutMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updateLoaiNhanVien(@RequestBody ViewDetailLoaiNhanVien viewLoaiNhanVienDTO)
            throws UpdateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            LoaiNhanVien loaiNhanVien= loaiNhanVienService.updateLoaiNhanVien(viewLoaiNhanVienDTO,viewLoaiNhanVienDTO.getMaLoaiNhanVien());
            ViewDetailLoaiNhanVien dto= loaiNhanVienConverter.convertToDTO(loaiNhanVien);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_UPDATE_LOAINHANVIEN);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật loại nhân viên: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_LOAINHANVIEN_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_LOAINHANVIEN_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> deleteLoaiNhanVien(@PathVariable Long id)
            throws DeleteDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            loaiNhanVienService.deleteLoaiNhanVien(id);
            responseDTO.setData("Xoa thanh cong loại nhân viên id: "+id);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_DELETE_LOAINHANVIEN);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật loại nhân viên: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_DELETE_LOAINHANVIEN_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_LOAINHANVIEN_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }
    
    
    
    
    
}
