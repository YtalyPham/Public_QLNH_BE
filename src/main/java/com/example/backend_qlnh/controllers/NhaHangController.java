package com.example.backend_qlnh.controllers;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.constants.SuccessCode;
import com.example.backend_qlnh.converter.NhaHangConverter;
import com.example.backend_qlnh.dto.NhaHangDTO.CreateNhaHangDTO;
import com.example.backend_qlnh.dto.NhaHangDTO.ViewNhaHangDTO;
import com.example.backend_qlnh.dto.ResponseDTO.ResponseDTO;
import com.example.backend_qlnh.entity.NhaHang;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.service.NhaHangService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/nhahang")
public class NhaHangController {
    @Autowired
    NhaHangConverter nhaHangConverter;

    @Autowired
    NhaHangService nhaHangService;

    private static final Logger LOGGER = LoggerFactory.getLogger(NhaHangController.class);

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> getAllNhaHang() throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<ViewNhaHangDTO> listDTO=nhaHangService.getListNhaHang();
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_NHAHANG_LIST_LOADED);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_NHAHANG_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list nha hang: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_NHAHANG_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_NHAHANG_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getNhaHangById(@PathVariable Long id) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            ViewNhaHangDTO NhaHangDTO= nhaHangService.getNhaHangById(id);
            if(NhaHangDTO!=null){
                responseDTO.setData(NhaHangDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_NHAHANG_FOUND);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_NHAHANG_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list nha hang: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_NHAHANG_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_NHAHANG_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> createNhaHang(@RequestBody CreateNhaHangDTO createNhaHangDTO)
            throws CreateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            NhaHang NhaHang= nhaHangService.createNhaHang(createNhaHangDTO);
            ViewNhaHangDTO ViewNhaHangDTO= nhaHangConverter.convertToDTO(NhaHang);
            responseDTO.setData(ViewNhaHangDTO);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_CREATE_NHAHANG);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi tạo nha hàng: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CREATE_NHAHANG_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_NHAHANG_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);

    }

    @PutMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updateNhaHang(@RequestBody ViewNhaHangDTO ViewNhaHangDTO)
            throws UpdateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            NhaHang NhaHang= nhaHangService.updateNhaHang(ViewNhaHangDTO,ViewNhaHangDTO.getMaNhaHang());
            ViewNhaHangDTO dto= nhaHangConverter.convertToDTO(NhaHang);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_UPDATE_NHAHANG);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật nhà hàng: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_NHAHANG_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_NHAHANG_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> deleteNhaHang(@PathVariable Long id)
            throws DeleteDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            nhaHangService.deleteNhaHang(id);
            responseDTO.setData("Xoa thanh cong nhà hàng id: "+id);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_DELETE_NHAHANG);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi xóa nhà hàng: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_DELETE_NHAHANG_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_NHAHANG_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }








}
