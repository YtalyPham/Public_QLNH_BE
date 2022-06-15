package com.example.backend_qlnh.controllers;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.constants.SuccessCode;
import com.example.backend_qlnh.converter.LoaiHinhSuKienConverter;
import com.example.backend_qlnh.dto.LoaiHinhSuKienDTO.CreateLoaiHinhSuKienDTO;
import com.example.backend_qlnh.dto.LoaiHinhSuKienDTO.ViewLoaiHinhSuKienDTO;
import com.example.backend_qlnh.dto.LoaiNhanVienDTO.CreateLoaiNhanVienDTO;
import com.example.backend_qlnh.dto.LoaiNhanVienDTO.ViewDetailLoaiNhanVien;
import com.example.backend_qlnh.dto.ResponseDTO.ResponseDTO;
import com.example.backend_qlnh.entity.LoaiHinhSuKien;
import com.example.backend_qlnh.entity.LoaiNhanVien;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.service.LoaiHinhSuKienService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/loaihinhsukien")
public class LoaiHinhSuKienController {
    @Autowired
    LoaiHinhSuKienService loaiHinhSuKienService;
    @Autowired
    LoaiHinhSuKienConverter loaiHinhSuKienConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoaiHinhSuKienController.class);

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> getAllLoaiHinhSuKien() throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<ViewLoaiHinhSuKienDTO> listDTO=loaiHinhSuKienService.getListLoaiHinhSuKien();
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_LOAIHINHSUKIEN_LIST_LOADED);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_LOAIHINHSUKIEN_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list loai hình sự kiện: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_LOAIHINHSUKIEN_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_LOAIHINHSUKIEN_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getLoaiHinhSuKienById(@PathVariable Long id) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            ViewLoaiHinhSuKienDTO LoaiHinhSuKienDTO= loaiHinhSuKienService.getLoaiHinhSuKienById(id);
            if(LoaiHinhSuKienDTO!=null){
                responseDTO.setData(LoaiHinhSuKienDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_LOAIHINHSUKIEN_FOUND);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_LOAIHINHSUKIEN_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list loại hình sự kiện: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_LOAIHINHSUKIEN_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_LOAIHINHSUKIEN_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> createNewLoaiHinhSuKien(@RequestBody CreateLoaiHinhSuKienDTO createLoaiHinhSuKienDTO)
            throws CreateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            LoaiHinhSuKien loaiHinhSuKien= loaiHinhSuKienService.createLoaiHinhSuKien(createLoaiHinhSuKienDTO);
            ViewLoaiHinhSuKienDTO viewLoaiHinhSuKienDTO= loaiHinhSuKienConverter.convertToDTO(loaiHinhSuKien);
            responseDTO.setData(viewLoaiHinhSuKienDTO);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_CREATE_LOAIHINHSUKIEN);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi tạo loại hình sự kiện: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CREATE_LOAIHINHSUKIEN_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_LOAIHINHSUKIEN_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);

    }

    @PutMapping("/")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updateLoaiHinhSuKien(@RequestBody ViewLoaiHinhSuKienDTO viewLoaiHinhSuKienDTO)
            throws UpdateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            LoaiHinhSuKien loaiHinhSuKien= loaiHinhSuKienService.updateLoaiHinhSuKien(viewLoaiHinhSuKienDTO,viewLoaiHinhSuKienDTO.getMaLoaiHinhSuKien());
            ViewLoaiHinhSuKienDTO dto= loaiHinhSuKienConverter.convertToDTO(loaiHinhSuKien);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_UPDATE_LOAIHINHSUKIEN);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật loại hình sự kiện: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_LOAIHINHSUKIEN_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_LOAIHINHSUKIEN_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> deleteLoaiHinhSuKien(@PathVariable Long id)
            throws DeleteDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            loaiHinhSuKienService.deleteLoaiHinhSuKien(id);
            responseDTO.setData("Xoa thanh cong loại hình sự kiện id: "+id);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_DELETE_LOAIHINHSUKIEN);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật loại hình sự kiện: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_DELETE_LOAIHINHSUKIEN_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_LOAIHINHSUKIEN_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }




}
