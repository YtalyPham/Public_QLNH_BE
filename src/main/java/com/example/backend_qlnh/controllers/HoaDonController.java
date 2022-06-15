package com.example.backend_qlnh.controllers;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.constants.SuccessCode;
import com.example.backend_qlnh.converter.HoaDonConverter;
import com.example.backend_qlnh.dto.HoaDonDTO.CreateHoaDonDTO;
import com.example.backend_qlnh.dto.HoaDonDTO.ViewHoaDonDTO;
import com.example.backend_qlnh.dto.PhieuDatTiecDTO.CreatePhieuDatTiecDTO;
import com.example.backend_qlnh.dto.PhieuDatTiecDTO.ViewPhieuDatTiecDTO;
import com.example.backend_qlnh.dto.ResponseDTO.ResponseDTO;
import com.example.backend_qlnh.entity.HoaDon;
import com.example.backend_qlnh.entity.PhieuDatTiec;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.service.HoaDonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/hoadon")
public class HoaDonController {
    @Autowired
    HoaDonService hoaDonService;
    @Autowired
    HoaDonConverter hoaDonConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(HoaDonController.class);

    @GetMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> getAllHoaDon() throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<ViewHoaDonDTO> listDTO=hoaDonService.getListHoaDon();
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_HOADON_LIST_LOADED);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_HOADON_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list HoaDon: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_HOADON_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_HOADON_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> getHoaDonById(@PathVariable Long id) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            ViewHoaDonDTO hoaDonDTO= hoaDonService.getHoaDonById(id);
            if(hoaDonDTO!=null){
                responseDTO.setData(hoaDonDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_HOADON_FOUND);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_HOADON_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list HoaDon: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_HOADON_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_HOADON_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> createNewHoaDon(@RequestBody CreateHoaDonDTO createHoaDonDTO)
            throws CreateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            HoaDon hoaDon= hoaDonService.createHoaDon(createHoaDonDTO);
            ViewHoaDonDTO viewHoaDonDTO= hoaDonConverter.convertToDTO(hoaDon);
            responseDTO.setData(viewHoaDonDTO);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_CREATE_HOADON);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi tạo HoaDon: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CREATE_HOADON_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_HOADON_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);

    }

    @PutMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updateHoaDon(@RequestBody ViewHoaDonDTO viewHoaDonDTO)
            throws UpdateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            HoaDon hoaDon= hoaDonService.updateHoaDon(viewHoaDonDTO,viewHoaDonDTO.getMaHoaDon());
            ViewHoaDonDTO dto= hoaDonConverter.convertToDTO(hoaDon);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_UPDATE_HOADON);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật HoaDon: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_HOADON_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_HOADON_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> deleteHoaDon(@PathVariable Long id)
            throws DeleteDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            hoaDonService.deleteHoaDon(id);
            responseDTO.setData("Xoa thanh cong HoaDon id: "+id);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_DELETE_HOADON);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật HoaDon: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_DELETE_HOADON_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_HOADON_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }
    
    
}
