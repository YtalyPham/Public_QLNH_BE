package com.example.backend_qlnh.controllers;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.constants.SuccessCode;
import com.example.backend_qlnh.converter.KhuyenMaiConverter;
import com.example.backend_qlnh.dto.KhuyenMaiDTO.ViewDetailKhuyenMaiDTO;
import com.example.backend_qlnh.dto.ResponseDTO.ResponseDTO;
import com.example.backend_qlnh.dto.KhuyenMaiDTO.CreateKhuyenMaiDTO;
import com.example.backend_qlnh.entity.KhuyenMai;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.service.KhuyenMaiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/khuyenmai")
public class KhuyenMaiController {
    @Autowired
    KhuyenMaiService khuyenMaiService;
    @Autowired
    KhuyenMaiConverter khuyenMaiConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(KhuyenMaiController.class);

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> getAllKhuyenMai() throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<ViewDetailKhuyenMaiDTO> listDTO=khuyenMaiService.getListKhuyenMai();
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_KHUYENMAI_LIST_LOADED);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_KHUYENMAI_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list khuyen mai: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_KHUYENMAI_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_KHUYENMAI_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getKhuyenMaiById(@PathVariable Long id) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            ViewDetailKhuyenMaiDTO KhuyenMaiDTO= khuyenMaiService.getKhuyenMaiById(id);
            if(KhuyenMaiDTO!=null){
                responseDTO.setData(KhuyenMaiDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_KHUYENMAI_FOUND);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_KHUYENMAI_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list khuyen mai: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_KHUYENMAI_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_KHUYENMAI_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> createNewKhuyenMai(@RequestBody CreateKhuyenMaiDTO createKhuyenMaiDTO)
            throws CreateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            KhuyenMai khuyenMai= khuyenMaiService.createKhuyenMai(createKhuyenMaiDTO);
            ViewDetailKhuyenMaiDTO viewDetailKhuyenMaiDTO= khuyenMaiConverter.convertToDTO(khuyenMai);
            responseDTO.setData(viewDetailKhuyenMaiDTO);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_CREATE_KHUYENMAI);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi tạo khuyen mai: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CREATE_KHUYENMAI_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_KHUYENMAI_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);

    }

    @PutMapping("/")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updateKhuyenMai(@RequestBody ViewDetailKhuyenMaiDTO viewDetailKhuyenMaiDTO)
            throws UpdateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            KhuyenMai khuyenMai= khuyenMaiService.updateKhuyenMai(viewDetailKhuyenMaiDTO,viewDetailKhuyenMaiDTO.getMaKhuyenMai());
            ViewDetailKhuyenMaiDTO dto= khuyenMaiConverter.convertToDTO(khuyenMai);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_UPDATE_KHUYENMAI);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật khuyen mai: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_KHUYENMAI_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_KHUYENMAI_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> deleteKhuyenMai(@PathVariable Long id)
            throws DeleteDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            khuyenMaiService.deleteKhuyenMai(id);
            responseDTO.setData("Xoa thanh cong khuyen mai id: "+id);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_DELETE_KHUYENMAI);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật khuyen mai: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_DELETE_KHUYENMAI_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_KHUYENMAI_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }
    
    
    
}
