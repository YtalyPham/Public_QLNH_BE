package com.example.backend_qlnh.controllers;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.constants.SuccessCode;
import com.example.backend_qlnh.converter.PhieuHenConverter;
import com.example.backend_qlnh.dto.KhachHangDTO.CreateKhachHangDTO;
import com.example.backend_qlnh.dto.KhachHangDTO.ViewKhachHangDTO;
import com.example.backend_qlnh.dto.PhieuHenDTO.CreatePhieuHenDTO;
import com.example.backend_qlnh.dto.PhieuHenDTO.ViewPhieuHenDTO;
import com.example.backend_qlnh.dto.ResponseDTO.ResponseDTO;
import com.example.backend_qlnh.entity.KhachHang;
import com.example.backend_qlnh.entity.PhieuHen;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.service.PhieuHenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/phieuhen")
public class PhieuHenController {
    @Autowired
    PhieuHenService phieuHenService;
    @Autowired
    PhieuHenConverter phieuHenConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(PhieuHenController.class);

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> getAllPhieuHen() throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<ViewPhieuHenDTO> listDTO=phieuHenService.getListPhieuHen();
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_PHIEUHEN_LIST_LOADED);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_PHIEUHEN_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list PhieuHen: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_PHIEUHEN_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUHEN_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getPhieuHenById(@PathVariable Long id) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            ViewPhieuHenDTO phieuHenDTO= phieuHenService.getPhieuHenById(id);
            if(phieuHenDTO!=null){
                responseDTO.setData(phieuHenDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_PHIEUHEN_FOUND);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_PHIEUHEN_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list PhieuHen: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_PHIEUHEN_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUHEN_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> createNewPhieuHen(@RequestBody CreatePhieuHenDTO createPhieuHenDTO)
            throws CreateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            PhieuHen phieuHen= phieuHenService.createPhieuHen(createPhieuHenDTO);
            ViewPhieuHenDTO viewPhieuHenDTO= phieuHenConverter.convertToDTO(phieuHen);
            responseDTO.setData(viewPhieuHenDTO);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_CREATE_PHIEUHEN);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi tạo PhieuHen: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CREATE_PHIEUHEN_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_PHIEUHEN_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);

    }

    @PutMapping("/")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updatePhieuHen(@RequestBody ViewPhieuHenDTO viewPhieuHenDTO)
            throws UpdateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            PhieuHen phieuHen= phieuHenService.updatePhieuHen(viewPhieuHenDTO,viewPhieuHenDTO.getMaPhieuHen());
            ViewPhieuHenDTO dto= phieuHenConverter.convertToDTO(phieuHen);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_UPDATE_PHIEUHEN);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật PhieuHen: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_PHIEUHEN_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_PHIEUHEN_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deletePhieuHen(@PathVariable Long id)
            throws DeleteDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            phieuHenService.deletePhieuHen(id);
            responseDTO.setData("Xoa thanh cong PhieuHen id: "+id);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_DELETE_PHIEUHEN);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật PhieuHen: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_DELETE_PHIEUHEN_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_PHIEUHEN_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    



}
