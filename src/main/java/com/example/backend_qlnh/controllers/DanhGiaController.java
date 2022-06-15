package com.example.backend_qlnh.controllers;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.constants.SuccessCode;
import com.example.backend_qlnh.converter.DanhGiaConverter;
import com.example.backend_qlnh.dto.DanhGiaDTO.CreateDanhGiaDTO;
import com.example.backend_qlnh.dto.DanhGiaDTO.ViewDanhGiaDTO;
import com.example.backend_qlnh.dto.KhachHangDTO.CreateKhachHangDTO;
import com.example.backend_qlnh.dto.KhachHangDTO.ViewKhachHangDTO;
import com.example.backend_qlnh.dto.ResponseDTO.ResponseDTO;
import com.example.backend_qlnh.entity.DanhGia;
import com.example.backend_qlnh.entity.KhachHang;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.service.DanhGiaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/danhgia")
public class DanhGiaController {
    @Autowired
    DanhGiaService danhGiaService;
    @Autowired
    DanhGiaConverter danhGiaConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(DanhGiaController.class);

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> getAllDanhGia() throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<ViewDanhGiaDTO> listDTO=danhGiaService.getListDanhGia();
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_DANHGIA_LIST_LOADED);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_DANHGIA_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list DanhGia: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_DANHGIA_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_DANHGIA_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getDanhGiaById(@PathVariable Long id) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            ViewDanhGiaDTO danhGiaDTO= danhGiaService.getDanhGiaById(id);
            if(danhGiaDTO!=null){
                responseDTO.setData(danhGiaDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_DANHGIA_FOUND);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_DANHGIA_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list DanhGia: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_DANHGIA_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_DANHGIA_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> createNewDanhGia(@RequestBody CreateDanhGiaDTO createDanhGiaDTO)
            throws CreateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            DanhGia danhGia= danhGiaService.createDanhGia(createDanhGiaDTO);
            ViewDanhGiaDTO viewDanhGiaDTO= danhGiaConverter.convertToDTO(danhGia);
            responseDTO.setData(viewDanhGiaDTO);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_CREATE_DANHGIA);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi tạo DanhGia: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CREATE_DANHGIA_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_DANHGIA_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);

    }

    @PutMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updateDanhGia(@RequestBody ViewDanhGiaDTO viewDanhGiaDTO)
            throws UpdateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            DanhGia danhGia= danhGiaService.updateDanhGia(viewDanhGiaDTO,viewDanhGiaDTO.getMaDanhGia());
            ViewDanhGiaDTO dto= danhGiaConverter.convertToDTO(danhGia);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_UPDATE_DANHGIA);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật DanhGia: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_DANHGIA_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_DANHGIA_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> deleteDanhGia(@PathVariable Long id)
            throws DeleteDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            danhGiaService.deleteDanhGia(id);
            responseDTO.setData("Xoa thanh cong DanhGia id: "+id);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_DELETE_DANHGIA);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật DanhGia: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_DELETE_DANHGIA_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_DANHGIA_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }
    
    
    
    
}
