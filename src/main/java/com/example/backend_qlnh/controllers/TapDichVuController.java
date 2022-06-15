package com.example.backend_qlnh.controllers;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.constants.SuccessCode;
import com.example.backend_qlnh.converter.TapDichVuConverter;
import com.example.backend_qlnh.dto.ResponseDTO.ResponseDTO;
import com.example.backend_qlnh.dto.TapDichVuDTO.CreateTapDichVuDTO;
import com.example.backend_qlnh.dto.TapDichVuDTO.NhieuTapDichVuDTO;
import com.example.backend_qlnh.entity.DichVu;
import com.example.backend_qlnh.entity.MonAn;
import com.example.backend_qlnh.entity.TapDichVu;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.repository.DichVuRepository;
import com.example.backend_qlnh.service.TapDichVuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/tapdichvu")
public class TapDichVuController {
    @Autowired
    private TapDichVuService tapDichVuService;
    @Autowired
    private TapDichVuConverter tapDichVuConverter;
    @Autowired
    private DichVuRepository dichVuRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(TapDichVuController.class);

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> getAllTapDichVu() throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<CreateTapDichVuDTO> listDTO=tapDichVuService.getListTapDichVu();
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_TAPDICHVU_LIST_LOADED);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_TAPDICHVU_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list tập dịch vụ: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_TAPDICHVU_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_TAPDICHVU_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/khongtrung")
    public ResponseEntity<ResponseDTO> getAllTapDichVuKhongTrung() throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<CreateTapDichVuDTO> listDTO=tapDichVuService.getListTapDichVuKhongTrung();
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_TAPDICHVU_LIST_LOADED);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_TAPDICHVU_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list tập dịch vụ: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_TAPDICHVU_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_TAPDICHVU_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getTapDichVuById(@PathVariable Long id) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            CreateTapDichVuDTO tapDichVuDTO= tapDichVuService.getTapDichVuById(id);
            if(tapDichVuDTO!=null){
                responseDTO.setData(tapDichVuDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_TAPDICHVU_FOUND);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_TAPDICHVU_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list tập dịch vụ: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_TAPDICHVU_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_TAPDICHVU_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/madichvu/{name}")
    public ResponseEntity<ResponseDTO> getMaDichVuById(@PathVariable String name) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<Long> listMaMonAn= tapDichVuService.getListMaDichVu(name);
            if(listMaMonAn!=null){
                responseDTO.setData(listMaMonAn);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_TAPDICHVU_FOUND);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_TAPDICHVU_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list mã dịch vụ: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_TAPDICHVU_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_TAPDICHVU_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> createNewTapDichVu(@RequestBody CreateTapDichVuDTO createTapDichVuDTO)
            throws CreateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            TapDichVu tapDichVu= tapDichVuService.createTapDichVu(createTapDichVuDTO);
            CreateTapDichVuDTO viewTapDichVuDTO= tapDichVuConverter.convertToDTO(tapDichVu);
            responseDTO.setData(viewTapDichVuDTO);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_CREATE_TAPDICHVU);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi tạo tập dịch vụ: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CREATE_TAPDICHVU_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_TAPDICHVU_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);

    }

    @PostMapping("/luunhieu")
    @PreAuthorize("hasRole('USER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> createNhieuTapDichVu(@RequestBody NhieuTapDichVuDTO nhieuTapDichVuDTO)
            throws CreateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        List<CreateTapDichVuDTO> listTDV= new ArrayList<>();

        int d= nhieuTapDichVuDTO.getMaNhieuDichVu().size();
        try{
            for(int i=0;i<d;i++){
                Optional<DichVu> dv1= dichVuRepository.findById((long) nhieuTapDichVuDTO.getMaNhieuDichVu().get(i));
                if(!dv1.isPresent()){
                    throw new DataNotFoundException(ErrorCode.ERR_DICHVU_NOT_FOUND);
                }
            }
            for(int i=0;i<d;i++){
                CreateTapDichVuDTO dto= new CreateTapDichVuDTO(nhieuTapDichVuDTO.getTenTapDichVu(),(long) nhieuTapDichVuDTO.getMaNhieuDichVu().get(i));
                TapDichVu tapDichVu= tapDichVuService.createTapDichVu(dto);
                CreateTapDichVuDTO viewTapDichVuDTO= tapDichVuConverter.convertToDTO(tapDichVu);
                listTDV.add(viewTapDichVuDTO);
            }

            responseDTO.setData(listTDV.get(0).getMaTapDichVu());
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_CREATE_TAPDICHVU);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi tạo tập dịch vụ: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CREATE_TAPDICHVU_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_TAPDICHVU_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);

    }

    @PutMapping("/")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updateTapDichVu(@RequestBody CreateTapDichVuDTO viewTapDichVuDTO)
            throws UpdateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            TapDichVu tapDichVu= tapDichVuService.updateTapDichVu(viewTapDichVuDTO,viewTapDichVuDTO.getMaTapDichVu());
            CreateTapDichVuDTO dto= tapDichVuConverter.convertToDTO(tapDichVu);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_UPDATE_TAPDICHVU);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật tập dịch vụ: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_TAPDICHVU_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_TAPDICHVU_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> deleteTapDichVu(@PathVariable Long id)
            throws DeleteDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            tapDichVuService.deleteTapDichVu(id);
            responseDTO.setData("Xoa thanh cong tập dịch vụ id: "+id);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_DELETE_TAPDICHVU);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật tập dịch vụ: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_DELETE_TAPDICHVU_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_TAPDICHVU_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }
}
