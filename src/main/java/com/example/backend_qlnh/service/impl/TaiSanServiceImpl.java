package com.example.backend_qlnh.service.impl;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.converter.TaiSanConverter;
import com.example.backend_qlnh.dto.TaiSanDTO.CreateTaiSanDTO;
import com.example.backend_qlnh.dto.TaiSanDTO.ViewTaiSanDTO;
import com.example.backend_qlnh.dto.TaiSanDTO.CreateTaiSanDTO;
import com.example.backend_qlnh.dto.TaiSanDTO.ViewTaiSanDTO;
import com.example.backend_qlnh.entity.HinhAnh;
import com.example.backend_qlnh.entity.NhaHang;
import com.example.backend_qlnh.entity.TaiSan;
import com.example.backend_qlnh.entity.TaiSan;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.repository.HinhAnhRepository;
import com.example.backend_qlnh.repository.NhaHangRepository;
import com.example.backend_qlnh.repository.TaiSanRepository;
import com.example.backend_qlnh.repository.TaiSanRepository;
import com.example.backend_qlnh.service.TaiSanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TaiSanServiceImpl implements TaiSanService {

    @Autowired
    TaiSanConverter taiSanConverter;
    @Autowired
    TaiSanRepository taiSanRepository;
    @Autowired
    HinhAnhRepository hinhAnhRepository;
    @Autowired
    NhaHangRepository nhaHangRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(TaiSanServiceImpl.class);

    @Override
    public TaiSan createTaiSan(CreateTaiSanDTO createTaiSanDTO) throws CreateDataFailException {
        TaiSan TaiSan;
        try{
            TaiSan=taiSanConverter.convertToEntity(createTaiSanDTO);
            return taiSanRepository.save(TaiSan);
        }catch (Exception ex){
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_NHAHANG_FAIL);
        }
    }

    @Override
    public TaiSan updateTaiSan(ViewTaiSanDTO viewDetailTaiSanDTO, Long id) throws UpdateDataFailException, DataNotFoundException {
        try{
            Optional<TaiSan> opt= taiSanRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("Tai san khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_TAISAN_NOT_FOUND);
            }
            TaiSan taiSan= opt.get();
            taiSan.setTenTaiSan(viewDetailTaiSanDTO.getTenTaiSan());
            taiSan.setSoLuong(viewDetailTaiSanDTO.getSoLuong());
            taiSan.setTinhTrang(viewDetailTaiSanDTO.isTinhTrang());
            taiSan.setGhiChu(viewDetailTaiSanDTO.getGhiChu());


            Optional<HinhAnh> optHinhAnh= hinhAnhRepository.findById(viewDetailTaiSanDTO.getMaHinhAnh());
            if(!optHinhAnh.isPresent()){
                LOGGER.info("HinhAnh khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
            }
            HinhAnh hinhAnh= optHinhAnh.get();
            taiSan.setHinhAnhTS(hinhAnh);

            Optional<NhaHang> optNhaHang= nhaHangRepository.findById(viewDetailTaiSanDTO.getMaNhahang());
            if(!optNhaHang.isPresent()){
                LOGGER.info("NhaHang khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_NHAHANG_NOT_FOUND);
            }
            NhaHang nhaHang= optNhaHang.get();
            taiSan.setNhahang(nhaHang);

            return taiSanRepository.save(taiSan);
        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_NHAHANG_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_NHAHANG_NOT_FOUND);
            }else if(message.equals(ErrorCode.ERR_HINHANH_NOT_FOUND)){
                throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
            }else{
                throw new DataNotFoundException(ErrorCode.ERR_TAISAN_NOT_FOUND);
            }

        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_TAISAN_FAIL);
        }
    }

    @Override
    public void deleteTaiSan(Long id) throws DeleteDataFailException, DataNotFoundException {
        try{
            Optional<TaiSan> opt= taiSanRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("TaiSan khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_TAISAN_NOT_FOUND);
            }
            taiSanRepository.deleteById(id);
        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_TAISAN_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_TAISAN_NOT_FOUND);
            }
        }catch(Exception ex){
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_TAISAN_FAIL);
        }
    }

    @Override
    public ViewTaiSanDTO getTaiSanById(Long id) throws DataNotFoundException {
        ViewTaiSanDTO viewTaiSanDTO;
        try{
            Optional<TaiSan> optTaiSan= taiSanRepository.findById(id);
            if(!optTaiSan.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_TAISAN_NOT_FOUND);
            }
            TaiSan TaiSan= optTaiSan.get();
            viewTaiSanDTO= taiSanConverter.convertToDTO(TaiSan);

        }catch (Exception exception){
            throw new DataNotFoundException(ErrorCode.ERR_TAISAN_NOT_FOUND);
        }
        return viewTaiSanDTO;
    }

    @Override
    public List<ViewTaiSanDTO> getListTaiSan() throws DataNotFoundException {
        List<ViewTaiSanDTO> listDTO;
        try{
            List<TaiSan> listEntity= taiSanRepository.findAll();
            listDTO=taiSanConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_TAISAN_LIST_LOADED_FAIL);
        }
        return listDTO;
    }
}
