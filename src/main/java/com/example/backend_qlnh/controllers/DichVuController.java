package com.example.backend_qlnh.controllers;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.constants.SuccessCode;
import com.example.backend_qlnh.converter.DichVuConverter;
import com.example.backend_qlnh.dto.DichVuDTO.CreateFEDichVuDTO;
import com.example.backend_qlnh.dto.QueryDTO.ListMaLong;
import com.example.backend_qlnh.dto.ResponseDTO.ResponseDTO;
import com.example.backend_qlnh.dto.DichVuDTO.CreateDichVuDTO;
import com.example.backend_qlnh.dto.DichVuDTO.ViewDichVuDTO;
import com.example.backend_qlnh.entity.DichVu;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.service.DichVuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/dichvu")
public class DichVuController {
    @Autowired
    DichVuService dichVuService;
    @Autowired
    DichVuConverter dichVuConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(DichVuController.class);

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> getAllDichVu() throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<ViewDichVuDTO> listDTO=dichVuService.getListDichVu();
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_DICHVU_LIST_LOADED);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_DICHVU_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list dich vu: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_DICHVU_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_DICHVU_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getDichVuById(@PathVariable Long id) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            ViewDichVuDTO dichVuDTO= dichVuService.getDichVuById(id);
            if(dichVuDTO!=null){
                responseDTO.setData(dichVuDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_DICHVU_FOUND);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_DICHVU_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list dich vu: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_DICHVU_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_DICHVU_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/timdichvutheopdt")
    public ResponseEntity<ResponseDTO> getAllDichVuTheoPDT(@RequestBody ListMaLong listid) throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<ViewDichVuDTO> listDTO=dichVuService.getListDichvuTheoPDT(listid.getListid());
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_DICHVU_LIST_LOADED);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_DICHVU_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list dich vu trung: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_DICHVU_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_DICHVU_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/tinhtien")
    public ResponseEntity<ResponseDTO> tinhTienDVList(@RequestBody ListMaLong listid) throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        double tt=0.0;
        try{
            List<ViewDichVuDTO> listDTO=dichVuService.getListDichvuTheoPDT(listid.getListid());
            int n= listDTO.toArray().length;
            for(int i=0;i<n;i++){
                tt+=listDTO.get(i).getDonGia();
            }
            if(listDTO.size()>0){
                responseDTO.setData(tt);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_DICHVU_LIST_LOADED);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_DICHVU_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list dich vu trung: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_DICHVU_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_DICHVU_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }


    @PostMapping("/")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> createNewDichVu(@RequestBody CreateDichVuDTO createDichVuDTO)
            throws CreateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            DichVu dichVu= dichVuService.createDichVu(createDichVuDTO);
            ViewDichVuDTO viewDichVuDTO= dichVuConverter.convertToDTO(dichVu);
            responseDTO.setData(viewDichVuDTO);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_CREATE_DICHVU);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi tạo dịch vụ: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CREATE_DICHVU_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_DICHVU_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);

    }

    @PutMapping("/")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updateDichVu(@RequestBody ViewDichVuDTO viewDichVuDTO)
            throws UpdateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            DichVu dichVu= dichVuService.updateDichVu(viewDichVuDTO,viewDichVuDTO.getMaDichVu());
            ViewDichVuDTO dto= dichVuConverter.convertToDTO(dichVu);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_UPDATE_DICHVU);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật dịch vụ: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_DICHVU_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_DICHVU_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> deleteDichVu(@PathVariable Long id)
            throws DeleteDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            dichVuService.deleteDichVu(id);
            responseDTO.setData("Xoa thanh cong dịch vụ id: "+id);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_DELETE_DICHVU);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật dịch vụ: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_DELETE_DICHVU_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_DICHVU_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }
    
    
    
    
}
