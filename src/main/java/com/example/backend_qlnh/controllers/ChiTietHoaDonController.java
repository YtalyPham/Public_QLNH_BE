package com.example.backend_qlnh.controllers;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.constants.SuccessCode;
import com.example.backend_qlnh.converter.ChiTietHoaDonConverter;
import com.example.backend_qlnh.dto.CTHDDTO.CreateChiTietHoaDonDTO;
import com.example.backend_qlnh.dto.CTHDDTO.TheoKhoangCthdDTO;
import com.example.backend_qlnh.dto.CTHDDTO.TheoNgayThangNamCthdDTO;
import com.example.backend_qlnh.dto.CTHDDTO.ViewChiTietHoaDonDTO;
import com.example.backend_qlnh.dto.DichVuDTO.ViewDichVuDTO;
import com.example.backend_qlnh.dto.QueryDTO.ListMaLong;
import com.example.backend_qlnh.dto.ResponseDTO.ResponseDTO;
import com.example.backend_qlnh.entity.ChiTietHoaDon;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.service.ChiTietHoaDonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/chitiethoadon")
public class ChiTietHoaDonController {
    @Autowired
    ChiTietHoaDonService chiTietHoaDonService;
    @Autowired
    ChiTietHoaDonConverter chiTietHoaDonConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(ChiTietHoaDonController.class);

    @GetMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> getAllChiTietHoaDon() throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<ViewChiTietHoaDonDTO> listDTO=chiTietHoaDonService.getListChiTietHoaDon();
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_CHITIETHOADON_LIST_LOADED);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_CHITIETHOADON_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list ChiTietHoaDon: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CHITIETHOADON_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_CHITIETHOADON_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/cthdtheongay")
   // @PreAuthorize("hasRole('USER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> getChiTietHoaDonTheoNgay(@RequestBody TheoNgayThangNamCthdDTO theoNgayThangNamCthdDTO) throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<Long> listDTO=chiTietHoaDonService.getCthdTheoNgay(theoNgayThangNamCthdDTO.getNgayLapHoaDon());
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_CHITIETHOADON_LIST_LOADED);
            }else{
                responseDTO.setData(null);
                responseDTO.setErrorCode(ErrorCode.ERR_CHITIETHOADON_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list ChiTietHoaDon: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CHITIETHOADON_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_CHITIETHOADON_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/cthdtheothang")
    // @PreAuthorize("hasRole('USER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> getChiTietHoaDonTheoThang(@RequestBody TheoNgayThangNamCthdDTO theoNgayThangNamCthdDTO) throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<Long> listDTO=chiTietHoaDonService.getCthdTheoThang(theoNgayThangNamCthdDTO.getNgayLapHoaDon());
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_CHITIETHOADON_LIST_LOADED);
            }else{
                responseDTO.setData(null);
                responseDTO.setErrorCode(ErrorCode.ERR_CHITIETHOADON_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list ChiTietHoaDon: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CHITIETHOADON_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_CHITIETHOADON_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/cthdtheonam")
    // @PreAuthorize("hasRole('USER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> getChiTietHoaDonTheoNam(@RequestBody TheoNgayThangNamCthdDTO theoNgayThangNamCthdDTO) throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<Long> listDTO=chiTietHoaDonService.getCthdTheoNam(theoNgayThangNamCthdDTO.getNgayLapHoaDon());
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_CHITIETHOADON_LIST_LOADED);
            }else{
                responseDTO.setData(null);
                responseDTO.setErrorCode(ErrorCode.ERR_CHITIETHOADON_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list ChiTietHoaDon: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CHITIETHOADON_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_CHITIETHOADON_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/cthdtheokhoang")
    // @PreAuthorize("hasRole('USER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> getChiTietHoaDonTheoKhoang(@RequestBody TheoKhoangCthdDTO theoKhoangCthdDTO) throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<Long> listDTO=chiTietHoaDonService.getCthdTheoKhoang(theoKhoangCthdDTO.getNgaydau(),theoKhoangCthdDTO.getNgaysau());
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_CHITIETHOADON_LIST_LOADED);
            }else{
                responseDTO.setData(null);
                responseDTO.setErrorCode(ErrorCode.ERR_CHITIETHOADON_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list ChiTietHoaDon: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CHITIETHOADON_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_CHITIETHOADON_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/tinhtongthanhtien")
    // @PreAuthorize("hasRole('USER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> tinhDoanhThuCTHD(@RequestBody ListMaLong listid) throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        double tt=0.0;
        try{
           List<ViewChiTietHoaDonDTO> listDTO=chiTietHoaDonService.getListCthdTheoListId(listid.getListid());
            int n= listDTO.size();
            for(int i=0;i<n;i++){
                tt=listDTO.get(i).getThanhTien()+tt;
            }
            if(listDTO.size()>0){
                responseDTO.setData(tt);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_CTHD_LIST_LOADED);
            }else{
                responseDTO.setData(null);
                responseDTO.setErrorCode(ErrorCode.ERR_CTHD_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list ChiTietHoaDon: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CHITIETHOADON_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_CHITIETHOADON_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> getChiTietHoaDonById(@PathVariable Long id) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            ViewChiTietHoaDonDTO chiTietHoaDonDTO= chiTietHoaDonService.getChiTietHoaDonById(id);
            if(chiTietHoaDonDTO!=null){
                responseDTO.setData(chiTietHoaDonDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_CHITIETHOADON_FOUND);
            }else{
                responseDTO.setData(null);
                responseDTO.setErrorCode(ErrorCode.ERR_CHITIETHOADON_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list ChiTietHoaDon: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CHITIETHOADON_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_CHITIETHOADON_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/maphieudattiec/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> getChiTietHoaDonByIdPdt(@PathVariable Long id) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            ViewChiTietHoaDonDTO chiTietHoaDonDTO= chiTietHoaDonService.getCthdByIdPDT(id);
            if(chiTietHoaDonDTO!=null){
                responseDTO.setData(chiTietHoaDonDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_CHITIETHOADON_FOUND);
            }else{
                responseDTO.setData(null);
                responseDTO.setErrorCode(ErrorCode.ERR_CHITIETHOADON_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list ChiTietHoaDon by PDT: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CHITIETHOADON_NOT_FOUND);
            responseDTO.setData(null);
           // throw new DataNotFoundException(ErrorCode.ERR_CHITIETHOADON_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> createNewChiTietHoaDon(@RequestBody CreateChiTietHoaDonDTO createChiTietHoaDonDTO)
            throws CreateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            ChiTietHoaDon chiTietHoaDon= chiTietHoaDonService.createChiTietHoaDon(createChiTietHoaDonDTO);
            ViewChiTietHoaDonDTO viewChiTietHoaDonDTO= chiTietHoaDonConverter.convertToDTO(chiTietHoaDon);
            responseDTO.setData(viewChiTietHoaDonDTO);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_CREATE_CHITIETHOADON);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi tạo ChiTietHoaDon: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CREATE_CHITIETHOADON_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_CHITIETHOADON_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);

    }

    @PutMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updateChiTietHoaDon(@RequestBody ViewChiTietHoaDonDTO viewChiTietHoaDonDTO)
            throws UpdateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            ChiTietHoaDon chiTietHoaDon= chiTietHoaDonService.updateChiTietHoaDon(viewChiTietHoaDonDTO,viewChiTietHoaDonDTO.getMaCthd());
            ViewChiTietHoaDonDTO dto= chiTietHoaDonConverter.convertToDTO(chiTietHoaDon);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_UPDATE_CHITIETHOADON);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật ChiTietHoaDon: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_CHITIETHOADON_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_CHITIETHOADON_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> deleteChiTietHoaDon(@PathVariable Long id)
            throws DeleteDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            chiTietHoaDonService.deleteChiTietHoaDon(id);
            responseDTO.setData("Xoa thanh cong ChiTietHoaDon id: "+id);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_DELETE_CHITIETHOADON);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật ChiTietHoaDon: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_DELETE_CHITIETHOADON_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_CHITIETHOADON_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }
    
    
    
    
    
}
