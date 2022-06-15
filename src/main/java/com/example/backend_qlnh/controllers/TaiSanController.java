package com.example.backend_qlnh.controllers;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.constants.SuccessCode;
import com.example.backend_qlnh.converter.TaiSanConverter;
import com.example.backend_qlnh.dto.TaiSanDTO.CreateTaiSanDTO;
import com.example.backend_qlnh.dto.TaiSanDTO.ViewTaiSanDTO;
import com.example.backend_qlnh.dto.ResponseDTO.ResponseDTO;
import com.example.backend_qlnh.entity.TaiSan;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.service.TaiSanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/taisan")
public class TaiSanController {
    @Autowired
    TaiSanService taiSanService;
    
    @Autowired
    TaiSanConverter taiSanConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(TaiSanController.class);

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> getAllTaiSan() throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<ViewTaiSanDTO> listDTO=taiSanService.getListTaiSan();
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_TAISAN_LIST_LOADED);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_TAISAN_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list tai san: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_TAISAN_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_TAISAN_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getTaiSanById(@PathVariable Long id) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            ViewTaiSanDTO TaiSanDTO= taiSanService.getTaiSanById(id);
            if(TaiSanDTO!=null){
                responseDTO.setData(TaiSanDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_TAISAN_FOUND);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_TAISAN_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list tai san: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_TAISAN_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_TAISAN_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> createNewTaiSan(@RequestBody CreateTaiSanDTO createTaiSanDTO)
            throws CreateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            TaiSan taiSan= taiSanService.createTaiSan(createTaiSanDTO);
            ViewTaiSanDTO viewTaiSanDTO= taiSanConverter.convertToDTO(taiSan);
            responseDTO.setData(viewTaiSanDTO);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_CREATE_TAISAN);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi tạo tài sản: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CREATE_TAISAN_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_TAISAN_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);

    }

    @PutMapping("/")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updateTaiSan(@RequestBody ViewTaiSanDTO ViewTaiSanDTO)
            throws UpdateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            TaiSan TaiSan= taiSanService.updateTaiSan(ViewTaiSanDTO,ViewTaiSanDTO.getMaTaiSan());
            ViewTaiSanDTO dto= taiSanConverter.convertToDTO(TaiSan);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_UPDATE_TAISAN);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật tài sản: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_TAISAN_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_TAISAN_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> deleteTaiSan(@PathVariable Long id)
            throws DeleteDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            taiSanService.deleteTaiSan(id);
            responseDTO.setData("Xoa thanh cong tài sản id: "+id);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_DELETE_TAISAN);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật tài sản: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_DELETE_TAISAN_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_TAISAN_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }


}
