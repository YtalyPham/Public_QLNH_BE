package com.example.backend_qlnh.service.impl;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.converter.SanhTiecConverter;
import com.example.backend_qlnh.dto.SanhTiecDTO.CreateSanhTiecDTO;
import com.example.backend_qlnh.dto.SanhTiecDTO.ViewSanhTiecDTO;
import com.example.backend_qlnh.dto.TaiSanDTO.ViewTaiSanDTO;
import com.example.backend_qlnh.entity.HinhAnh;
import com.example.backend_qlnh.entity.NhaHang;
import com.example.backend_qlnh.entity.SanhTiec;
import com.example.backend_qlnh.entity.TaiSan;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.repository.HinhAnhRepository;
import com.example.backend_qlnh.repository.SanhTiecRepository;
import com.example.backend_qlnh.service.SanhTiecService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SanhTiecServiceImpl implements SanhTiecService {
    @Autowired
    SanhTiecRepository sanhTiecRepository;
    @Autowired
    SanhTiecConverter sanhTiecConverter;
    @Autowired
    HinhAnhRepository hinhAnhRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(SanhTiecServiceImpl.class);

    @Override
    public SanhTiec createSanhTiec(CreateSanhTiecDTO createSanhTiecDTO) throws CreateDataFailException {
        SanhTiec sanhTiec;
        try{
            sanhTiec=sanhTiecConverter.convertToEntity(createSanhTiecDTO);
            return sanhTiecRepository.save(sanhTiec);
        }catch (Exception ex){
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_SANHTIEC_FAIL);
        }
    }

    @Override
    public SanhTiec updateSanhTiec(ViewSanhTiecDTO viewSanhTiecDTO, Long id) throws UpdateDataFailException, DataNotFoundException {
        try{
            Optional<SanhTiec> opt= sanhTiecRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("Sanh tiec khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_SANHTIEC_NOT_FOUND);
            }
            SanhTiec sanhTiec= opt.get();
            sanhTiec.setTenSanhTiec(viewSanhTiecDTO.getTenSanhTiec());
            sanhTiec.setViTri(viewSanhTiecDTO.getViTri());
            sanhTiec.setQuayTrienLam(viewSanhTiecDTO.getQuayTrienLam());
            sanhTiec.setDienTich(viewSanhTiecDTO.getDienTich());
            sanhTiec.setDonGia(viewSanhTiecDTO.getDonGia());
            sanhTiec.setKieuRapHat(viewSanhTiecDTO.getKieuRapHat());
            sanhTiec.setKieuLopHoc(viewSanhTiecDTO.getKieuLopHoc());
            sanhTiec.setKieuBanTron(viewSanhTiecDTO.getKieuBanTron());
            sanhTiec.setKieuBanDai(viewSanhTiecDTO.getKieuBanDai());
            sanhTiec.setKichThuoc(viewSanhTiecDTO.getKichThuoc());


            Optional<HinhAnh> optHinhAnh= hinhAnhRepository.findById(viewSanhTiecDTO.getMaHinhAnh());
            if(!optHinhAnh.isPresent()){
                LOGGER.info("HinhAnh khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
            }
            HinhAnh hinhAnh= optHinhAnh.get();
            sanhTiec.setHinhAnhST(hinhAnh);

            return sanhTiecRepository.save(sanhTiec);
        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_HINHANH_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
            }else{
                throw new DataNotFoundException(ErrorCode.ERR_SANHTIEC_NOT_FOUND);
            }

        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_SANHTIEC_FAIL);
        }
    }

    @Override
    public void deleteSanhTiec(Long id) throws DeleteDataFailException, DataNotFoundException {
        try{
            Optional<SanhTiec> opt= sanhTiecRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("SanhTiec khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_SANHTIEC_NOT_FOUND);
            }
            sanhTiecRepository.deleteById(id);
        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_SANHTIEC_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_SANHTIEC_NOT_FOUND);
            }
        }catch(Exception ex){
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_SANHTIEC_FAIL);
        }
    }

    @Override
    public ViewSanhTiecDTO getSanhTiecById(Long id) throws DataNotFoundException {
        ViewSanhTiecDTO viewSanhTiecDTO;
        try{
            Optional<SanhTiec> opt= sanhTiecRepository.findById(id);
            if(!opt.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_SANHTIEC_NOT_FOUND);
            }
            SanhTiec sanhTiec= opt.get();
            viewSanhTiecDTO= sanhTiecConverter.convertToDTO(sanhTiec);

        }catch (Exception exception){
            throw new DataNotFoundException(ErrorCode.ERR_SANHTIEC_NOT_FOUND);
        }
        return viewSanhTiecDTO;
    }

    @Override
    public List<ViewSanhTiecDTO> getListSanhTiec() throws DataNotFoundException {
        List<ViewSanhTiecDTO> listDTO;
        try{
            List<SanhTiec> listEntity= sanhTiecRepository.findAll();
            listDTO=sanhTiecConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_SANHTIEC_LIST_LOADED_FAIL);
        }
        return listDTO;
    }

    @Override
    public List<ViewSanhTiecDTO> getListSanhTiecKhongTrung(List<Long> mast) throws DataNotFoundException {
        List<ViewSanhTiecDTO> listDTO;
        try{
            List<SanhTiec> listEntity= sanhTiecRepository.timSanhTiecKhongTrung(mast);
            listDTO=sanhTiecConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_SANHTIEC_LIST_LOADED_FAIL);
        }
        return listDTO;
    }
}
