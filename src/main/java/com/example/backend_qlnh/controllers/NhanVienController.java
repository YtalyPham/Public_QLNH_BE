package com.example.backend_qlnh.controllers;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.constants.SuccessCode;
import com.example.backend_qlnh.converter.NhanVienConverter;
import com.example.backend_qlnh.dto.KhachHangDTO.CreateKhachHangDTO;
import com.example.backend_qlnh.dto.KhachHangDTO.ViewKhachHangDTO;
import com.example.backend_qlnh.dto.NhanVienDTO.CreateNhanVienDTO;
import com.example.backend_qlnh.dto.NhanVienDTO.ViewNhanVienDTO;
import com.example.backend_qlnh.dto.ResponseDTO.ResponseDTO;
import com.example.backend_qlnh.entity.KhachHang;
import com.example.backend_qlnh.entity.NhanVien;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.service.NhanVienService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/nhanvien")
public class NhanVienController {
    @Autowired
    NhanVienService nhanVienService;
    @Autowired
    NhanVienConverter nhanVienConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(NhanVienController.class);

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> getAllNhanVien() throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<ViewNhanVienDTO> listDTO=nhanVienService.getListNhanVien();
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_NHANVIEN_LIST_LOADED);
            }else{
                responseDTO.setData(listDTO);
                responseDTO.setErrorCode(ErrorCode.ERR_NHANVIEN_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list NhanVien: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_NHANVIEN_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_NHANVIEN_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getNhanVienById(@PathVariable Long id) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            ViewNhanVienDTO nhanVienDTO= nhanVienService.getNhanVienById(id);
            if(nhanVienDTO!=null){
                responseDTO.setData(nhanVienDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_NHANVIEN_FOUND);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_NHANVIEN_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list NhanVien: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_NHANVIEN_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_NHANVIEN_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/demslnhanvien")
    public ResponseEntity<ResponseDTO> demSoLuongNhanVien() throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            Integer sl= nhanVienService.demslNhanVien();
            if(sl!=null){
                responseDTO.setData(sl);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_NHANVIEN_FOUND);
            }else{
                responseDTO.setData(0);
                responseDTO.setErrorCode(ErrorCode.ERR_NHANVIEN_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load so luong nhanvien: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_NHANVIEN_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_NHANVIEN_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/mataikhoan/{id}")
    public ResponseEntity<ResponseDTO> getNhanVienByMaTK(@PathVariable Long id) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            ViewNhanVienDTO nhanVienDTO= nhanVienService.getNVByMaTK(id);
            if(nhanVienDTO!=null){
                responseDTO.setData(nhanVienDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_NHANVIEN_FOUND);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_NHANVIEN_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list NhanVien by MaTaiKhoan: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_NHANVIEN_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_NHANVIEN_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> createNewNhanVien(@RequestBody CreateNhanVienDTO createNhanVienDTO)
            throws CreateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            NhanVien nhanVien= nhanVienService.createNhanVien(createNhanVienDTO);
            ViewNhanVienDTO viewNhanVienDTO= nhanVienConverter.convertToDTO(nhanVien);
            responseDTO.setData(viewNhanVienDTO);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_CREATE_NHANVIEN);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi tạo NhanVien: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CREATE_NHANVIEN_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_NHANVIEN_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);

    }

    @PutMapping("/")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updateNhanVien(@RequestBody ViewNhanVienDTO viewNhanVienDTO)
            throws UpdateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            NhanVien nhanVien= nhanVienService.updateNhanVien(viewNhanVienDTO,viewNhanVienDTO.getMaNhanVien());
            ViewNhanVienDTO dto= nhanVienConverter.convertToDTO(nhanVien);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_UPDATE_NHANVIEN);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật NhanVien: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_NHANVIEN_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_NHANVIEN_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> deleteNhanVien(@PathVariable Long id)
            throws DeleteDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            nhanVienService.deleteNhanVien(id);
            responseDTO.setData("Xoa thanh cong NhanVien id: "+id);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_DELETE_NHANVIEN);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật NhanVien: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_DELETE_NHANVIEN_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_NHANVIEN_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/diemdanh/{id}")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> diemdanhNhanVien(@PathVariable Long id)
            throws DeleteDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            int dd= nhanVienService.diemDanh(id);
            responseDTO.setData("So ngay lam nhan vien sau khi diem danh: "+dd);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_DIEMDANH);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi điểm danh NhanVien: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_DIEMDANH);
            throw new DeleteDataFailException(ErrorCode.ERR_DIEMDANH);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    
}
