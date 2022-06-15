package com.example.backend_qlnh.controllers;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.constants.SuccessCode;
import com.example.backend_qlnh.converter.NguyenLieuConverter;
import com.example.backend_qlnh.dto.MonAnDTO.ViewDetailMonAnDTO;
import com.example.backend_qlnh.dto.NguyenLieuDTO.CreateNguyenLieuDTO;

import com.example.backend_qlnh.dto.NguyenLieuDTO.ViewDetailNguyenLieuDTO;
import com.example.backend_qlnh.dto.QueryDTO.ListMaLong;
import com.example.backend_qlnh.dto.ResponseDTO.ResponseDTO;
import com.example.backend_qlnh.entity.NguyenLieu;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.service.NguyenLieuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/nguyenlieu")
public class NguyenLieuController {
    @Autowired
    NguyenLieuService nguyenLieuService;
    @Autowired
    NguyenLieuConverter nguyenLieuConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(NguyenLieuController.class);

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> getAllNguyenLieu() throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<ViewDetailNguyenLieuDTO> listDTO=nguyenLieuService.getListNguyenLieu();
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_NGUYENLIEU_LIST_LOADED);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_NGUYENLIEU_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list nguyên liệu: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_NGUYENLIEU_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_NGUYENLIEU_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getNguyenLieuById(@PathVariable Long id) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            ViewDetailNguyenLieuDTO nguyenLieuDTO= nguyenLieuService.getNguyenLieuById(id);
            if(nguyenLieuDTO!=null){
                responseDTO.setData(nguyenLieuDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_NGUYENLIEU_FOUND);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_NGUYENLIEU_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list nguyên liệu: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_NGUYENLIEU_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_NGUYENLIEU_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> createNewNguyenLieu(@RequestBody CreateNguyenLieuDTO createNguyenLieuDTO)
            throws CreateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            NguyenLieu nguyenLieu= nguyenLieuService.createNguyenLieu(createNguyenLieuDTO);
            ViewDetailNguyenLieuDTO viewNguyenLieuDTO= nguyenLieuConverter.convertToDTO(nguyenLieu);
            responseDTO.setData(viewNguyenLieuDTO);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_CREATE_NGUYENLIEU);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi tạo nguyên liệu: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CREATE_NGUYENLIEU_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_NGUYENLIEU_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);

    }

    @PostMapping("/timnguyenlieutheomonan")
    public ResponseEntity<ResponseDTO> getAllNguyenLieuTheoMA(@RequestBody ListMaLong listid) throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<ViewDetailNguyenLieuDTO> listDTO=nguyenLieuService.getListNguyenLieuTheoMonAn(listid.getListid());
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_NGUYENLIEU_LIST_LOADED);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_NGUYENLIEU_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list nguyen lieu: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_NGUYENLIEU_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_NGUYENLIEU_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updateNguyenLieu(@RequestBody ViewDetailNguyenLieuDTO viewNguyenLieuDTO)
            throws UpdateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            NguyenLieu nguyenLieu= nguyenLieuService.updateNguyenLieu(viewNguyenLieuDTO,viewNguyenLieuDTO.getMaNguyenLieu());
            ViewDetailNguyenLieuDTO dto= nguyenLieuConverter.convertToDTO(nguyenLieu);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_UPDATE_NGUYENLIEU);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật nguyên liệu: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_NGUYENLIEU_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_NGUYENLIEU_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> deleteNguyenLieu(@PathVariable Long id)
            throws DeleteDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            nguyenLieuService.deleteNguyenLieu(id);
            responseDTO.setData("Xoa thanh cong nguyên liệu id: "+id);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_DELETE_NGUYENLIEU);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật nguyên liệu: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_DELETE_NGUYENLIEU_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_NGUYENLIEU_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }



}
