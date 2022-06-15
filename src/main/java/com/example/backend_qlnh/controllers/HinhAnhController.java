package com.example.backend_qlnh.controllers;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.constants.SuccessCode;
import com.example.backend_qlnh.converter.HinhAnhConverter;
import com.example.backend_qlnh.dto.HinhAnhDTO.CreateHinhAnhDTO;
import com.example.backend_qlnh.dto.HinhAnhDTO.ViewDetailHinhAnhDTO;
import com.example.backend_qlnh.dto.ResponseDTO.ResponseDTO;
import com.example.backend_qlnh.entity.HinhAnh;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.repository.HinhAnhRepository;
import com.example.backend_qlnh.service.HinhAnhService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/hinhanh")
public class HinhAnhController {
    @Autowired
    private HinhAnhService hinhAnhService;

    @Autowired
    private HinhAnhConverter hinhAnhConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(HinhAnhController.class);

    @GetMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> getAllHinhAnh() throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<ViewDetailHinhAnhDTO> listDTO=hinhAnhService.getListHinhAnh();
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_HINHANH_LIST_LOADED);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_HINHANH_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list hình ảnh: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_HINHANH_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_HINHANH_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> getHinhAnhById(@PathVariable Long id) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            ViewDetailHinhAnhDTO hinhAnhDTO= hinhAnhService.getHinhAnhById(id);
            if(hinhAnhDTO!=null){
                responseDTO.setData(hinhAnhDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_HINHANH_FOUND);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_HINHANH_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list hình ảnh: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_HINHANH_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> createHinhAnh(@RequestBody CreateHinhAnhDTO createHinhAnhDTO)
            throws CreateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            HinhAnh hinhAnh= hinhAnhService.createHinhAnh(createHinhAnhDTO);
            ViewDetailHinhAnhDTO viewDetailHinhAnhDTO= hinhAnhConverter.convertToDTO(hinhAnh);
            responseDTO.setData(viewDetailHinhAnhDTO);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_CREATE_HINHANH);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi tạo hình ảnh: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CREATE_HINHANH_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_HINHANH_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);

    }

    @PutMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updateHinhAnh(@RequestBody ViewDetailHinhAnhDTO viewDetailHinhAnhDTO)
            throws UpdateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            HinhAnh hinhAnh= hinhAnhService.updateHinhAnh(viewDetailHinhAnhDTO,viewDetailHinhAnhDTO.getMaHinhAnh());
            ViewDetailHinhAnhDTO dto= hinhAnhConverter.convertToDTO(hinhAnh);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_UPDATE_HINHANH);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật hình ảnh: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_HINHANH_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_HINHANH_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> deleteHinhAnh(@PathVariable Long id)
            throws DeleteDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            hinhAnhService.deleteHinhAnh(id);
            responseDTO.setData("Xoa thanh cong hinh anh id: "+id);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_DELETE_HINHANH);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật hình ảnh: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_DELETE_HINHANH_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_HINHANH_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }








}
