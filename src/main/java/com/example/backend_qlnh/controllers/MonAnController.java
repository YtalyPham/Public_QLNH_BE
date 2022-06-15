package com.example.backend_qlnh.controllers;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.constants.SuccessCode;
import com.example.backend_qlnh.converter.MonAnConverter;
import com.example.backend_qlnh.dto.DichVuDTO.ViewDichVuDTO;
import com.example.backend_qlnh.dto.MonAnDTO.CreateMonAnDTO;
import com.example.backend_qlnh.dto.MonAnDTO.NhieuMonAnDTO;
import com.example.backend_qlnh.dto.MonAnDTO.UpdateNhieuMonAnDTO;
import com.example.backend_qlnh.dto.MonAnDTO.ViewDetailMonAnDTO;
import com.example.backend_qlnh.dto.NguyenLieuDTO.CreateNguyenLieuDTO;
import com.example.backend_qlnh.dto.NguyenLieuDTO.ViewDetailNguyenLieuDTO;
import com.example.backend_qlnh.dto.QueryDTO.ListMaLong;
import com.example.backend_qlnh.dto.QueryDTO.NameStringDTO;
import com.example.backend_qlnh.dto.ResponseDTO.ResponseDTO;
import com.example.backend_qlnh.dto.TapDichVuDTO.CreateTapDichVuDTO;
import com.example.backend_qlnh.entity.DichVu;
import com.example.backend_qlnh.entity.MonAn;
import com.example.backend_qlnh.entity.NguyenLieu;
import com.example.backend_qlnh.entity.TapDichVu;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.repository.NguyenLieuRepository;
import com.example.backend_qlnh.service.MonAnService;
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
@RequestMapping("/monan")
public class MonAnController {
    @Autowired
    private MonAnService monAnService;
    @Autowired
    private MonAnConverter monAnConverter;
    @Autowired
    private NguyenLieuRepository nguyenLieuRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(MonAnController.class);

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> getAllMonAn() throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<ViewDetailMonAnDTO> listDTO=monAnService.getListMonAn();
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_MONAN_LIST_LOADED);
            }else{
                responseDTO.setData(null);
                responseDTO.setErrorCode(ErrorCode.ERR_MONAN_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list món ăn: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_MONAN_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_MONAN_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/khongtrung")
    public ResponseEntity<ResponseDTO> getMonAnKhongTrung() throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<ViewDetailMonAnDTO> listDTO=monAnService.getListMonAnKhongTrung();
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_MONAN_LIST_LOADED);
            }else{
                responseDTO.setData(null);
                responseDTO.setErrorCode(ErrorCode.ERR_MONAN_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list món ăn: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_MONAN_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_MONAN_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/theoloai")
    public ResponseEntity<ResponseDTO> getMonAnTheoLoaiMonAn(@RequestBody NameStringDTO nameStringDTO) throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<ViewDetailMonAnDTO> listDTO=monAnService.getListMAtheoLoaiMA(nameStringDTO.getName());
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_MONAN_LIST_LOADED);
            }else{
                responseDTO.setData(null);
                responseDTO.setErrorCode(ErrorCode.ERR_MONAN_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list món ăn: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_MONAN_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_MONAN_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/manguyenlieu")
    public ResponseEntity<ResponseDTO> getMaNguyenLieuTheoMonAn(@RequestBody NameStringDTO nameStringDTO) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<Long> listMaNguyenLieu= monAnService.getListMaNguyenLieu(nameStringDTO.getName());
            if(listMaNguyenLieu!=null){
                responseDTO.setData(listMaNguyenLieu);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_MONAN_FOUND);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_MONAN_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list mã nguyên liệu: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_MONAN_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_MONAN_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/listmamonan")
    public ResponseEntity<ResponseDTO> getMaMonAnTheoMonAn(@RequestBody NameStringDTO nameStringDTO) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<Long> listMaMonAn= monAnService.getListMaMonAn(nameStringDTO.getName());
            if(listMaMonAn!=null){
                responseDTO.setData(listMaMonAn);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_MONAN_FOUND);
            }else{
                responseDTO.setData(null);
                responseDTO.setErrorCode(ErrorCode.ERR_MONAN_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list mã món ăn: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_MONAN_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_MONAN_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/deletenhieu")
    public ResponseEntity<ResponseDTO> deleteNhieuMaMonAn(@RequestBody ListMaLong listMaLong) throws DeleteDataFailException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            int n=listMaLong.getListid().toArray().length;
            for(int i=0;i<n;i++){
                monAnService.deleteMonAn(listMaLong.getListid().get(i));
                responseDTO.setData("Xoa thanh cong món ăn id: ");
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_DELETE_MONAN);
            }
        }catch (Exception ex){
            LOGGER.info("Lỗi khi xóa món ăn: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_DELETE_MONAN_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_MONAN_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/updatenhieu")
   // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updateNhieuMonAn(@RequestBody UpdateNhieuMonAnDTO updateNhieuMonAnDTO)
            throws UpdateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            int n= updateNhieuMonAnDTO.getListid().toArray().length;
            for(int i=0;i<n;i++){
                MonAn monAn= monAnService.updateNhieuMonAn(updateNhieuMonAnDTO,updateNhieuMonAnDTO.getListid().get(i));
                // ViewDetailMonAnDTO dto= monAnConverter.convertToDTO(monAn);
                responseDTO.setData("Update thanh cong");
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_UPDATE_MONAN);
            }


        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật món ăn: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_MONAN_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_MONAN_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getMonAnById(@PathVariable Long id) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            ViewDetailMonAnDTO monAnDTO= monAnService.getMonAnById(id);
            if(monAnDTO!=null){
                responseDTO.setData(monAnDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_MONAN_FOUND);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_MONAN_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list món ăn: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_MONAN_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_MONAN_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }
    @PostMapping("/timmonantheothucdon")
    public ResponseEntity<ResponseDTO> getAllMonAnTheoTD(@RequestBody ListMaLong listid) throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<ViewDetailMonAnDTO> listDTO=monAnService.getListMonAnTheoThucDon(listid.getListid());
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_MONAN_LIST_LOADED);
            }else{
                responseDTO.setData(null);
                responseDTO.setErrorCode(ErrorCode.ERR_MONAN_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list mon an: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_MONAN_LIST_LOADED_FAIL);
            responseDTO.setData(null);

        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/tinhtien")
    public ResponseEntity<ResponseDTO> tinhTongTienMA(@RequestBody ListMaLong listid) throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        double tt=0.0;
        try{
            List<ViewDetailMonAnDTO> listDTO=monAnService.getListMonAnTheoThucDon(listid.getListid());
            int n= listDTO.toArray().length;
            for(int i=0;i<n;i++){
                tt+=listDTO.get(i).getDonGia();
            }
            if(listDTO.size()>0){
                responseDTO.setData(tt);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_MONAN_LIST_LOADED);
            }else{
                responseDTO.setData(-1);
                responseDTO.setErrorCode(ErrorCode.ERR_MONAN_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi tinh tien mon an: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_MONAN_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_MONAN_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> createNewMonAn(@RequestBody CreateMonAnDTO createMonAnDTO)
            throws CreateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            MonAn monAn= monAnService.createMonAn(createMonAnDTO);
            ViewDetailMonAnDTO viewMonAnDTO= monAnConverter.convertToDTO(monAn);
            responseDTO.setData(viewMonAnDTO);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_CREATE_MONAN);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi tạo món ăn: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CREATE_MONAN_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_MONAN_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);

    }

    @PostMapping("/luunhieu")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> createMonAnNhieuNguyenLieu(@RequestBody NhieuMonAnDTO nhieuMonAnDTO)
            throws CreateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        List<ViewDetailMonAnDTO> listTDV= new ArrayList<>();

        int d= nhieuMonAnDTO.getMaNhieuNguyenLieu().size();
        try{
            for(int i=0;i<d;i++){
                Optional<NguyenLieu> dv1= nguyenLieuRepository.findById((long) nhieuMonAnDTO.getMaNhieuNguyenLieu().get(i));
                if(!dv1.isPresent()){
                    throw new DataNotFoundException(ErrorCode.ERR_NGUYENLIEU_NOT_FOUND);
                }
            }
            for(int i=0;i<d;i++){
                CreateMonAnDTO dto= new CreateMonAnDTO(nhieuMonAnDTO.getTenMonAn(), nhieuMonAnDTO.getLoaiMonAn(),
                        nhieuMonAnDTO.getDonGia(), nhieuMonAnDTO.getMaHinhAnh(),
                        (long) nhieuMonAnDTO.getMaNhieuNguyenLieu().get(i));
                MonAn monAn= monAnService.createMonAn(dto);
                ViewDetailMonAnDTO viewMonAnDTO= monAnConverter.convertToDTO(monAn);
                listTDV.add(viewMonAnDTO);
            }

            responseDTO.setData(listTDV);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_CREATE_MONAN);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi tạo món ăn: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CREATE_MONAN_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_MONAN_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);

    }

    @PutMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updateMonAn(@RequestBody ViewDetailMonAnDTO viewMonAnDTO)
            throws UpdateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            MonAn monAn= monAnService.updateMonAn(viewMonAnDTO,viewMonAnDTO.getMaMonAn());
            ViewDetailMonAnDTO dto= monAnConverter.convertToDTO(monAn);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_UPDATE_MONAN);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật món ăn: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_MONAN_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_MONAN_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> deleteMonAn(@PathVariable Long id)
            throws DeleteDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            monAnService.deleteMonAn(id);
            responseDTO.setData("Xoa thanh cong món ăn id: "+id);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_DELETE_MONAN);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi xóa món ăn: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_DELETE_MONAN_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_MONAN_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }
    
    
    
    
}
