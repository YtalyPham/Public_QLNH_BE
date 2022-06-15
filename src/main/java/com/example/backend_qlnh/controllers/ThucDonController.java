package com.example.backend_qlnh.controllers;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.constants.SuccessCode;
import com.example.backend_qlnh.converter.ThucDonConverter;
import com.example.backend_qlnh.dto.QueryDTO.ListMaLong;
import com.example.backend_qlnh.dto.QueryDTO.NameStringDTO;
import com.example.backend_qlnh.dto.ResponseDTO.ResponseDTO;
import com.example.backend_qlnh.dto.TapDichVuDTO.CreateTapDichVuDTO;
import com.example.backend_qlnh.dto.ThucDonDTO.CreateThucDonDTO;
import com.example.backend_qlnh.dto.ThucDonDTO.NhieuThucDonDTO;
import com.example.backend_qlnh.dto.ThucDonDTO.UpdateNhieuThucDonDTO;
import com.example.backend_qlnh.dto.ThucDonDTO.ViewThucDonDTO;
import com.example.backend_qlnh.entity.DichVu;
import com.example.backend_qlnh.entity.MonAn;
import com.example.backend_qlnh.entity.TapDichVu;
import com.example.backend_qlnh.entity.ThucDon;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.repository.MonAnRepository;
import com.example.backend_qlnh.service.ThucDonService;
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
@RequestMapping("/thucdon")
public class ThucDonController {
    @Autowired
    private ThucDonService thucDonService;
    @Autowired
    private ThucDonConverter thucDonConverter;
    @Autowired
    private MonAnRepository monAnRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ThucDonController.class);

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> getAllThucDon() throws DataNotFoundException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<ViewThucDonDTO> listDTO=thucDonService.getListThucDon();
            if(listDTO.size()>0){
                responseDTO.setData(listDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_THUCDON_LIST_LOADED);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_THUCDON_LIST_LOADED_FAIL);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list thực đơn: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_THUCDON_LIST_LOADED_FAIL);
            throw new DataNotFoundException(ErrorCode.ERR_THUCDON_LIST_LOADED_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> getThucDonById(@PathVariable Long id) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            ViewThucDonDTO thucDonDTO= thucDonService.getThucDonById(id);
            if(thucDonDTO!=null){
                responseDTO.setData(thucDonDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_THUCDON_FOUND);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_THUCDON_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list thực đơn: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_THUCDON_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_THUCDON_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/mamonan")
    public ResponseEntity<ResponseDTO> getMaMonAnById(@RequestBody NameStringDTO nameStringDTO) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<Long> listMaMonAn= thucDonService.getListMaMonAn(nameStringDTO.getName());
            if(listMaMonAn!=null){
                responseDTO.setData(listMaMonAn);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_THUCDON_FOUND);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_THUCDON_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list mã món ăn: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_THUCDON_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_THUCDON_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/listmathucdon")
    public ResponseEntity<ResponseDTO> getListMaTheoTenTD(@RequestBody NameStringDTO nameStringDTO) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<Long> listMaMonAn= thucDonService.getListMaThucDonTheoTenTD(nameStringDTO.getName());
            if(listMaMonAn!=null){
                responseDTO.setData(listMaMonAn);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_THUCDON_FOUND);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_THUCDON_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list mã thực đơn: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_THUCDON_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_THUCDON_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/deletelistmathucdon")
    public ResponseEntity<ResponseDTO> deleteListMaTheoTenTD(@RequestBody ListMaLong listMaLong) throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            int n=listMaLong.getListid().toArray().length;
            for(int i=0;i<n;i++){
                thucDonService.deleteThucDon(listMaLong.getListid().get(i));
                responseDTO.setData("Xoa thanh cong thực đơn id: ");
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_DELETE_THUCDON);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list mã thực đơn: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_THUCDON_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_THUCDON_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/updatelistmathucdon")
    //@PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updateListMaTheoTenTD(@RequestBody UpdateNhieuThucDonDTO updateNhieuThucDonDTO)
            throws UpdateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            int n=updateNhieuThucDonDTO.getListid().toArray().length;
            for(int i=0;i<n;i++){
                ThucDon thucDon= thucDonService.updateNhieuThucDon(updateNhieuThucDonDTO,updateNhieuThucDonDTO.getListid().get(i));
                responseDTO.setData("Update nhieu thuc don thanh cong");
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_UPDATE_THUCDON);
            }


        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật thực đơn: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_THUCDON_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_THUCDON_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/khongtrung")
    public ResponseEntity<ResponseDTO> getThucDonKhongTrung() throws DataNotFoundException{
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            List<ViewThucDonDTO> listMaMonAn= thucDonService.getListThucDonKhongTrung();
            if(listMaMonAn!=null){
                responseDTO.setData(listMaMonAn);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_THUCDON_FOUND);
            }else{
                responseDTO.setData(false);
                responseDTO.setErrorCode(ErrorCode.ERR_THUCDON_NOT_FOUND);
            }

        }catch (Exception exception){
            LOGGER.info("Lỗi khi load list mã món ăn: " + exception.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_THUCDON_NOT_FOUND);
            throw new DataNotFoundException(ErrorCode.ERR_THUCDON_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> createNewThucDon(@RequestBody CreateThucDonDTO createThucDonDTO)
            throws CreateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            ThucDon thucDon= thucDonService.createThucDon(createThucDonDTO);
            ViewThucDonDTO viewThucDonDTO= thucDonConverter.convertToDTO(thucDon);
            responseDTO.setData(viewThucDonDTO);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_CREATE_THUCDON);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi tạo Thực đơn: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CREATE_THUCDON_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_THUCDON_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);

    }

    @PostMapping("/luunhieu")
    @PreAuthorize("hasRole('USER') or hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> createNhieuThucDon(@RequestBody NhieuThucDonDTO nhieuThucDonDTO)
            throws CreateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        List<ViewThucDonDTO> listTDV= new ArrayList<>();

        int d= nhieuThucDonDTO.getMaNhieuMonAn().size();
        try{
            for(int i=0;i<d;i++){
                Optional<MonAn> dv1= monAnRepository.findById((long) nhieuThucDonDTO.getMaNhieuMonAn().get(i));
                if(!dv1.isPresent()){
                    throw new DataNotFoundException(ErrorCode.ERR_MONAN_NOT_FOUND);
                }
            }
            for(int i=0;i<d;i++){
                CreateThucDonDTO dto= new CreateThucDonDTO(nhieuThucDonDTO.getTenThucDon(),
                        nhieuThucDonDTO.getSetThucDon(),nhieuThucDonDTO.getDonGia(),nhieuThucDonDTO.getMacDinh()
                        ,(long) nhieuThucDonDTO.getMaNhieuMonAn().get(i),nhieuThucDonDTO.getMaHinhAnh());
                ThucDon thucDon= thucDonService.createThucDon(dto);
                ViewThucDonDTO viewThucDonDTO= thucDonConverter.convertToDTO(thucDon);
                listTDV.add(viewThucDonDTO);
            }

            responseDTO.setData(listTDV.get(0).getMaThucDon());
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_CREATE_THUCDON);
        }catch (Exception ex){
            LOGGER.info("Lỗi khi tạo Thực đơn: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_CREATE_THUCDON_FAIL);
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_THUCDON_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);

    }

    @PutMapping("/")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> updateThucDon(@RequestBody ViewThucDonDTO viewThucDonDTO)
            throws UpdateDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            ThucDon thucDon= thucDonService.updateThucDon(viewThucDonDTO,viewThucDonDTO.getMaThucDon());
            ViewThucDonDTO dto= thucDonConverter.convertToDTO(thucDon);
            responseDTO.setData(dto);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_UPDATE_THUCDON);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật thực đơn: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_UPDATE_THUCDON_FAIL);
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_THUCDON_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('EMPLOYEE') or hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> deleteThucDon(@PathVariable Long id)
            throws DeleteDataFailException {
        ResponseDTO responseDTO= new ResponseDTO();
        try{
            thucDonService.deleteThucDon(id);
            responseDTO.setData("Xoa thanh cong thực đơn id: "+id);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_DELETE_THUCDON);

        }catch (Exception ex){
            LOGGER.info("Lỗi khi cập nhật thực đơn: " + ex.getMessage());
            responseDTO.setErrorCode(ErrorCode.ERR_DELETE_THUCDON_FAIL);
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_THUCDON_FAIL);
        }
        return ResponseEntity.ok().body(responseDTO);
    }
    


}
