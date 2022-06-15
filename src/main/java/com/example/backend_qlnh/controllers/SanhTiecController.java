package com.example.backend_qlnh.controllers;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.constants.SuccessCode;
import com.example.backend_qlnh.converter.SanhTiecConverter;
import com.example.backend_qlnh.dto.QueryDTO.ListMaLong;
import com.example.backend_qlnh.dto.ResponseDTO.ResponseDTO;
import com.example.backend_qlnh.dto.SanhTiecDTO.CreateSanhTiecDTO;
import com.example.backend_qlnh.dto.SanhTiecDTO.ViewSanhTiecDTO;
import com.example.backend_qlnh.entity.SanhTiec;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.service.SanhTiecService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/sanhtiec")
public class SanhTiecController {
    @Autowired
    SanhTiecConverter sanhTiecConverter;
    @Autowired
    SanhTiecService sanhTiecService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SanhTiecController.class);

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> getAllSanhTiec() throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<ViewSanhTiecDTO> listDTO=sanhTiecService.getListSanhTiec();
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_SANHTIEC_LIST_LOADED);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_SANHTIEC_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list sanh tiec: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_SANHTIEC_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_SANHTIEC_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getSanhTiecById(@PathVariable Long id) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            ViewSanhTiecDTO sanhTiecDTO= sanhTiecService.getSanhTiecById(id);
            if(sanhTiecDTO!=null){
                responseDTO.setData(sanhTiecDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_SANHTIEC_FOUND);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_SANHTIEC_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list sanh tiec: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_SANHTIEC_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_SANHTIEC_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/timsanhtieckhongtrung")
    public ResponseEntity<ResponseDTO> getSanhTiecKhongTrung(@RequestBody ListMaLong dv) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<ViewSanhTiecDTO> sanhTiecDTO= sanhTiecService.getListSanhTiecKhongTrung(dv.getListid());
            if(sanhTiecDTO!=null){
                responseDTO.setData(sanhTiecDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_SANHTIEC_FOUND);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_SANHTIEC_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list sanh tiec: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_SANHTIEC_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_SANHTIEC_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> createNewSanhTiec(@RequestBody CreateSanhTiecDTO createSanhTiecDTO)
            throws CreateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            SanhTiec sanhTiec= sanhTiecService.createSanhTiec(createSanhTiecDTO);
            ViewSanhTiecDTO viewSanhTiecDTO= sanhTiecConverter.convertToDTO(sanhTiec);
            responseDTO.setData(viewSanhTiecDTO);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_CREATE_SANHTIEC);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi tạo sảnh tiệc: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CREATE_SANHTIEC_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_SANHTIEC_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);

    }

    @PutMapping("/")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updateSanhTiec(@RequestBody ViewSanhTiecDTO viewSanhTiecDTO)
            throws UpdateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            SanhTiec sanhTiec= sanhTiecService.updateSanhTiec(viewSanhTiecDTO,viewSanhTiecDTO.getMaSanhTiec());
            ViewSanhTiecDTO dto= sanhTiecConverter.convertToDTO(sanhTiec);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_UPDATE_SANHTIEC);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật sảnh tiệc: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_SANHTIEC_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_SANHTIEC_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> deleteSanhTiec(@PathVariable Long id)
            throws DeleteDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            sanhTiecService.deleteSanhTiec(id);
            responseDTO.setData("Xoa thanh cong sảnh tiệc id: "+id);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_DELETE_SANHTIEC);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật sảnh tiệc: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_DELETE_SANHTIEC_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_SANHTIEC_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

}
