package com.example.backend_qlnh.controllers;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.constants.SuccessCode;
import com.example.backend_qlnh.converter.PhieuDatTiecConverter;
import com.example.backend_qlnh.dto.CTHDDTO.TheoNgayThangNamCthdDTO;
import com.example.backend_qlnh.dto.PhieuDatTiecDTO.CreatePhieuDatTiecDTO;
import com.example.backend_qlnh.dto.PhieuDatTiecDTO.UpdateLHDTO;
import com.example.backend_qlnh.dto.PhieuDatTiecDTO.UpdateTCDTO;
import com.example.backend_qlnh.dto.PhieuDatTiecDTO.ViewPhieuDatTiecDTO;
import com.example.backend_qlnh.dto.QueryDTO.ListMaLong;
import com.example.backend_qlnh.dto.QueryDTO.PDTtheoKhNgayBuoi;
import com.example.backend_qlnh.dto.QueryDTO.PDTtheoNgayBuoi;
import com.example.backend_qlnh.dto.ResponseDTO.ResponseDTO;
import com.example.backend_qlnh.entity.PhieuDatTiec;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.service.PhieuDatTiecService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/phieudattiec")
public class PhieuDatTiecController {
    @Autowired
    PhieuDatTiecService phieuDatTiecService;
    @Autowired
    PhieuDatTiecConverter phieuDatTiecConverter;

    private static final Logger LOGGER = LoggerFactory.getLogger(PhieuDatTiecController.class);

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> getAllPhieuDatTiec() throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<ViewPhieuDatTiecDTO> listDTO=phieuDatTiecService.getListPhieuDatTiec();
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_PHIEUDATTIEC_LIST_LOADED);
            }else{
                responseDTO.setData(listDTO);
                responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list PhieuDatTiec: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/listdathanhtoan")
    public ResponseEntity<ResponseDTO> getAllPhieuDatTiecDaTT() throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<ViewPhieuDatTiecDTO> listDTO=phieuDatTiecService.getListPhieuDatTiecDaTT();
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_PHIEUDATTIEC_LIST_LOADED);
            }else{
                responseDTO.setData(listDTO);
                responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list PhieuDatTiec đã thanh toán: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/listchuathanhtoan")
    public ResponseEntity<ResponseDTO> getAllPhieuDatTiecChuaTT() throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<ViewPhieuDatTiecDTO> listDTO=phieuDatTiecService.getListPhieuDatTiecChuaTT();
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_PHIEUDATTIEC_LIST_LOADED);
            }else{
                responseDTO.setData(listDTO);
                responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list PhieuDatTiec chưa thanh toán: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/timpdttheomakh/{makh}")
    public ResponseEntity<ResponseDTO> getAllPDTTheoMaKH(@PathVariable String makh) throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        Long makhnew= Long.parseLong(makh);
        try{
            List<ViewPhieuDatTiecDTO> listDTO=phieuDatTiecService.getListPhieuDatTiecTheoMaKH(makhnew);
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_PHIEUDATTIEC_LIST_LOADED);
            }else{
                responseDTO.setData(listDTO);
                responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
            }
        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list PhieuDatTiec theo makh: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/timpdtchuatttheomakh/{makh}")
    public ResponseEntity<ResponseDTO> getAllPDTChuaTTTheoMaKH(@PathVariable String makh) throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        Long makhnew= Long.parseLong(makh);
        try{
            List<ViewPhieuDatTiecDTO> listDTO=phieuDatTiecService.getListPDTChuaThanhToanTheoMaKH(makhnew);
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_PHIEUDATTIEC_LIST_LOADED);
            }else{
                responseDTO.setData(listDTO);
                responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
            }
        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list PhieuDatTiec theo makh: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/timpdtdatttheomakh/{makh}")
    public ResponseEntity<ResponseDTO> getAllPDTDaTTTheoMaKH(@PathVariable String makh) throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        Long makhnew= Long.parseLong(makh);
        try{
            List<ViewPhieuDatTiecDTO> listDTO=phieuDatTiecService.getListPDTDaThanhToanTheoMaKH(makhnew);
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_PHIEUDATTIEC_LIST_LOADED);
            }else{
                responseDTO.setData(listDTO);
                responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
            }
        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list PhieuDatTiec theo makh: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }








    @PostMapping("/timtheomakhvangay/{makh}")
    public ResponseEntity<ResponseDTO> getAllPDTTheoMaKHvaNgay(@PathVariable Long makh, @RequestParam("date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime ngay) throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<ViewPhieuDatTiecDTO> listDTO=phieuDatTiecService.getListPhieuDatTiecTheoMakhNgay(makh,ngay);
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_PHIEUDATTIEC_LIST_LOADED);
            }else{
                responseDTO.setData(listDTO);
                responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list PhieuDatTiec theo ngay va makh: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/timtheomakhngaybuoi")
    public ResponseEntity<ResponseDTO> getAllPDTTheoMaKHNgayBuoi(@RequestBody PDTtheoKhNgayBuoi pdt) throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<ViewPhieuDatTiecDTO> listDTO=phieuDatTiecService.getListPhieuDatTiecTheoMakhNgayBuoi(pdt.getMaKhachHang(),pdt.getNgayToChuc(),pdt.getBuoi());
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_PHIEUDATTIEC_LIST_LOADED);
            }else{
                responseDTO.setData(listDTO);
                responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list PhieuDatTiec theo ngay va makh va buoi: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/getpdttheolistmapdt")
    public ResponseEntity<ResponseDTO> getAllPDTTheoListMaPDT(@RequestBody ListMaLong listMaLong) throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<ViewPhieuDatTiecDTO> listDTO=phieuDatTiecService.getListPhieuDatTiecTheoListMaPDT(listMaLong.getListid());
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_PHIEUDATTIEC_LIST_LOADED);
            }else{
                responseDTO.setData(listDTO);
                responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list PhieuDatTiec theo ngay va makh va buoi: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/mapdttheongaytochuc")
    // @PreAuthorize("hasRole('USER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> getPDTTheoNgayToChuc(@RequestBody TheoNgayThangNamCthdDTO theoNgayThangNamCthdDTO) throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<Long> listDTO=phieuDatTiecService.getPDTTheoNgayTC(theoNgayThangNamCthdDTO.getNgayLapHoaDon());
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_PHIEUDATTIEC_LIST_LOADED);
            }else{
                responseDTO.setData(null);
                responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list PhieuDatTiec theo ngayto chuc: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getPhieuDatTiecById(@PathVariable Long id) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            ViewPhieuDatTiecDTO phieuDatTiecDTO= phieuDatTiecService.getPhieuDatTiecById(id);
            if(phieuDatTiecDTO!=null){
                responseDTO.setData(phieuDatTiecDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_PHIEUDATTIEC_FOUND);
            }else{
                responseDTO.setData(null);
                responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list PhieuDatTiec: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/chuathanhtoan/{id}")
    public ResponseEntity<ResponseDTO> getPDTchuaThanhToan(@PathVariable Long id) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            Integer sl= phieuDatTiecService.getPDTchuaTT(id);
            if(sl!=null){
                responseDTO.setData(sl);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_PHIEUDATTIEC_FOUND);
            }else{
                responseDTO.setData(0);
                responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi tim PDT chua thanh toan: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/dempdtdatt")
    public ResponseEntity<ResponseDTO> getPDTdaThanhToan() throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            Integer sl= phieuDatTiecService.demslPDTdaTT();
            if(sl!=null){
                responseDTO.setData(sl);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_PHIEUDATTIEC_FOUND);
            }else{
                responseDTO.setData(0);
                responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi dem PDT chua da thanh toan: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/dempdtchuatt")
    public ResponseEntity<ResponseDTO> demPDTchuaThanhToan() throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            Integer sl= phieuDatTiecService.demslPDTchuaTT();
            if(sl!=null){
                responseDTO.setData(sl);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_PHIEUDATTIEC_FOUND);
            }else{
                responseDTO.setData(0);
                responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi dem PDT chua thanh toan: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/dempdthomnay")
    public ResponseEntity<ResponseDTO> demPDTtronghomnay(@RequestBody TheoNgayThangNamCthdDTO theoNgayThangNamCthdDTO) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            Integer sl= phieuDatTiecService.demslPDTtochuchomnay(theoNgayThangNamCthdDTO.getNgayLapHoaDon());
            if(sl!=null){
                responseDTO.setData(sl);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_PHIEUDATTIEC_FOUND);
            }else{
                responseDTO.setData(0);
                responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi dem PDT chua thanh toan hom nay: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/timmasttheongaybuoi")
    public ResponseEntity<ResponseDTO> getSanhTiecByMaKhBuoiNgay(@RequestBody PDTtheoNgayBuoi pdt) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<Long> phieuDatTiecDTO= phieuDatTiecService.getListMaSanhTiecTheoNgayBuoi(pdt.getNgayToChuc(),pdt.getBuoi());
            if(phieuDatTiecDTO!=null){
                responseDTO.setData(phieuDatTiecDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_PHIEUDATTIEC_FOUND);
            }else{
                responseDTO.setData(phieuDatTiecDTO);
                responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list PhieuDatTiec: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

//    @PostMapping("/timmadvtheomakhngaybuoi")
//    public ResponseEntity<ResponseDTO> getDichvuByMaKhBuoiNgay(@RequestBody PDTtheoKhNgayBuoi pdt) throws DataNotFoundException{
//        ResponseDTO responseDTO= new ResponseDTO();
//        try{
//            List<Long> phieuDatTiecDTO= phieuDatTiecService.getListMaDichVuTheoMaKhNgayBuoi(pdt.getMaKhachHang(),pdt.getNgayToChuc(),pdt.getBuoi());
//            if(phieuDatTiecDTO!=null){
//                responseDTO.setData(phieuDatTiecDTO);
//                responseDTO.setSuccessCode(SuccessCode.SUCCESS_PHIEUDATTIEC_FOUND);
//            }else{
//                responseDTO.setData(false);
//                responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
//            }
//
//        }catch (Exception exception){
//            LOGGER.info("Lỗi khi load list PhieuDatTiec: " + exception.getMessage());
//            responseDTO.setErrorCode(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
//            throw new DataNotFoundException(ErrorCode.ERR_PHIEUDATTIEC_NOT_FOUND);
//        }
//        return ResponseEntity.ok().body(responseDTO);
//    }

    @PostMapping("/")
    public ResponseEntity<ResponseDTO> createNewPhieuDatTiec(@RequestBody CreatePhieuDatTiecDTO createPhieuDatTiecDTO)
            throws CreateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            PhieuDatTiec phieuDatTiec= phieuDatTiecService.createPhieuDatTiec(createPhieuDatTiecDTO);
            ViewPhieuDatTiecDTO viewPhieuDatTiecDTO= phieuDatTiecConverter.convertToDTO(phieuDatTiec);
            responseDTO.setData(viewPhieuDatTiecDTO);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_CREATE_PHIEUDATTIEC);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi tạo PhieuDatTiec: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CREATE_PHIEUDATTIEC_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_PHIEUDATTIEC_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);

    }

    @PutMapping("/")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updatePhieuDatTiec(@RequestBody ViewPhieuDatTiecDTO viewPhieuDatTiecDTO)
            throws UpdateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            PhieuDatTiec phieuDatTiec= phieuDatTiecService.updatePhieuDatTiec(viewPhieuDatTiecDTO,viewPhieuDatTiecDTO.getMaPhieuDatTiec());
            ViewPhieuDatTiecDTO dto= phieuDatTiecConverter.convertToDTO(phieuDatTiec);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_UPDATE_PHIEUDATTIEC);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật PhieuDatTiec: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_PHIEUDATTIEC_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_PHIEUDATTIEC_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/updatelichhen")
//    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updateLichHenPhieuDatTiec(@RequestBody UpdateLHDTO updateLHDTO)
            throws UpdateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        Long idpdt= Long.parseLong(updateLHDTO.getMaPhieuDatTiec());
        try{
            PhieuDatTiec phieuDatTiec= phieuDatTiecService.updateLichHenPhieuDatTiec(updateLHDTO.getLichHen(),idpdt);
            ViewPhieuDatTiecDTO dto= phieuDatTiecConverter.convertToDTO(phieuDatTiec);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_UPDATE_PHIEUDATTIEC);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật Lịch hẹn PhieuDatTiec: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_PHIEUDATTIEC_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_PHIEUDATTIEC_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/updatethanhtoan/{id}")
//    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updateThanhToanPhieuDatTiec(@PathVariable String id)
            throws UpdateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        Long idpdt= Long.parseLong(id);
        try{
            PhieuDatTiec phieuDatTiec= phieuDatTiecService.updateThanhToanPhieuDatTiec(idpdt);
            ViewPhieuDatTiecDTO dto= phieuDatTiecConverter.convertToDTO(phieuDatTiec);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_UPDATE_PHIEUDATTIEC);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật thanh toán PhieuDatTiec: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_PHIEUDATTIEC_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_PHIEUDATTIEC_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/updatetiencoc")
//    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updateTienCocPhieuDatTiec(@RequestBody UpdateTCDTO updateTCDTO)
            throws UpdateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        Long idpdt= Long.parseLong(updateTCDTO.getMaPhieuDatTiec());
        try{
            PhieuDatTiec phieuDatTiec= phieuDatTiecService.updateTienCocPhieuDatTiec(updateTCDTO.getTienCoc(),idpdt);
            ViewPhieuDatTiecDTO dto= phieuDatTiecConverter.convertToDTO(phieuDatTiec);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_UPDATE_PHIEUDATTIEC);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật tiencoc PhieuDatTiec: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_PHIEUDATTIEC_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_PHIEUDATTIEC_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> deletePhieuDatTiec(@PathVariable Long id)
            throws DeleteDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            phieuDatTiecService.deletePhieuDatTiec(id);
            responseDTO.setData("Xoa thanh cong PhieuDatTiec id: "+id);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_DELETE_PHIEUDATTIEC);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật PhieuDatTiec: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_DELETE_PHIEUDATTIEC_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_PHIEUDATTIEC_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }







}
