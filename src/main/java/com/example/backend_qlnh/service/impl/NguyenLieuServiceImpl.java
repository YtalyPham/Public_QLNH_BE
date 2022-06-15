package com.example.backend_qlnh.service.impl;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.converter.NguyenLieuConverter;
import com.example.backend_qlnh.dto.DichVuDTO.ViewDichVuDTO;
import com.example.backend_qlnh.dto.MonAnDTO.ViewDetailMonAnDTO;
import com.example.backend_qlnh.dto.NguyenLieuDTO.CreateNguyenLieuDTO;
import com.example.backend_qlnh.dto.NguyenLieuDTO.ViewDetailNguyenLieuDTO;
import com.example.backend_qlnh.entity.DichVu;
import com.example.backend_qlnh.entity.HinhAnh;
import com.example.backend_qlnh.entity.MonAn;
import com.example.backend_qlnh.entity.NguyenLieu;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.repository.NguyenLieuRepository;
import com.example.backend_qlnh.service.NguyenLieuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NguyenLieuServiceImpl implements NguyenLieuService {

    @Autowired
    NguyenLieuRepository nguyenLieuRepository;
    @Autowired
    NguyenLieuConverter nguyenLieuConverter;
    private static final Logger LOGGER = LoggerFactory.getLogger(NguyenLieuServiceImpl.class);
    @Override
    public NguyenLieu createNguyenLieu(CreateNguyenLieuDTO createNguyenLieuDTO) throws CreateDataFailException {
        NguyenLieu nguyenLieu;
        try{
            nguyenLieu=nguyenLieuConverter.convertToEntity(createNguyenLieuDTO);
            return nguyenLieuRepository.save(nguyenLieu);
        }catch (Exception ex){
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_NGUYENLIEU_FAIL);
        }
    }

    @Override
    public NguyenLieu updateNguyenLieu(ViewDetailNguyenLieuDTO viewNguyenLieuDTO, Long id) throws UpdateDataFailException, DataNotFoundException {

        try{
            Optional<NguyenLieu> opt= nguyenLieuRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("Nguyên liệu khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_NGUYENLIEU_NOT_FOUND);
            }
            NguyenLieu nguyenLieu= opt.get();
            nguyenLieu.setTenNguyenLieu(viewNguyenLieuDTO.getTenNguyenLieu());
            nguyenLieu.setNgayNhap(viewNguyenLieuDTO.getNgayNhap());
            nguyenLieu.setNgayHetHan(viewNguyenLieuDTO.getNgayHetHan());
            nguyenLieu.setGhiChu(viewNguyenLieuDTO.getGhiChu());
            nguyenLieu.setDonGiaMua(viewNguyenLieuDTO.getDonGiaMua());
            nguyenLieu.setKg(viewNguyenLieuDTO.getKg());
            return nguyenLieuRepository.save(nguyenLieu);
        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_NGUYENLIEU_FAIL);
        }
    }

    @Override
    public void deleteNguyenLieu(Long id) throws DeleteDataFailException, DataNotFoundException {
        try{
            Optional<NguyenLieu> opt= nguyenLieuRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("Nguyên liệu khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_NGUYENLIEU_NOT_FOUND);
            }
            nguyenLieuRepository.deleteById(id);
        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_NGUYENLIEU_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_NGUYENLIEU_NOT_FOUND);
            }
        }catch(Exception ex){
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_NGUYENLIEU_FAIL);
        }
    }

    @Override
    public ViewDetailNguyenLieuDTO getNguyenLieuById(Long id) throws DataNotFoundException {
        ViewDetailNguyenLieuDTO viewDichVuDTO;
        try{
            Optional<NguyenLieu> opt= nguyenLieuRepository.findById(id);
            if(!opt.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_NGUYENLIEU_NOT_FOUND);
            }
            NguyenLieu nguyenLieu= opt.get();
            viewDichVuDTO= nguyenLieuConverter.convertToDTO(nguyenLieu);

        }catch (Exception exception){
            throw new DataNotFoundException(ErrorCode.ERR_NGUYENLIEU_NOT_FOUND);
        }
        return viewDichVuDTO;
    }

    @Override
    public List<ViewDetailNguyenLieuDTO> getListNguyenLieu() throws DataNotFoundException {
        List<ViewDetailNguyenLieuDTO> listDTO;
        try{
            List<NguyenLieu> listEntity= nguyenLieuRepository.findAll();
            listDTO=nguyenLieuConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_NGUYENLIEU_LIST_LOADED_FAIL);
        }
        return listDTO;
    }

    @Override
    public List<ViewDetailNguyenLieuDTO> getListNguyenLieuTheoMonAn(List<Long> manl) throws DataNotFoundException {
        List<ViewDetailNguyenLieuDTO> listDTO;
        try{
            List<NguyenLieu> listEntity= nguyenLieuRepository.timNguyenLieuTheoMA(manl);
            listDTO=nguyenLieuConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_DICHVU_LIST_LOADED_FAIL);
        }
        return listDTO;
    }
}
