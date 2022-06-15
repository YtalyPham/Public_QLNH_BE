package com.example.backend_qlnh.service.impl;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.converter.ThucDonConverter;
import com.example.backend_qlnh.dto.MonAnDTO.ViewDetailMonAnDTO;
import com.example.backend_qlnh.dto.ThucDonDTO.CreateThucDonDTO;
import com.example.backend_qlnh.dto.ThucDonDTO.UpdateNhieuThucDonDTO;
import com.example.backend_qlnh.dto.ThucDonDTO.ViewThucDonDTO;
import com.example.backend_qlnh.entity.HinhAnh;
import com.example.backend_qlnh.entity.MonAn;
import com.example.backend_qlnh.entity.NguyenLieu;
import com.example.backend_qlnh.entity.ThucDon;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.repository.HinhAnhRepository;
import com.example.backend_qlnh.repository.MonAnRepository;
import com.example.backend_qlnh.repository.ThucDonRepository;
import com.example.backend_qlnh.service.ThucDonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ThucDonServiceImpl implements ThucDonService {
    @Autowired
    ThucDonRepository thucDonRepository;
    @Autowired
    ThucDonConverter thucDonConverter;
    @Autowired
    HinhAnhRepository hinhAnhRepository;
    @Autowired
    MonAnRepository monAnRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(ThucDonServiceImpl.class);
    @Override
    public ThucDon createThucDon(CreateThucDonDTO createThucDonDTO) throws CreateDataFailException {
        ThucDon thucDon;
        try{
            thucDon=thucDonConverter.convertToEntity(createThucDonDTO);
            return thucDonRepository.save(thucDon);
        }catch (Exception ex){
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_THUCDON_FAIL);
        }
    }

    @Override
    public ThucDon updateThucDon(ViewThucDonDTO viewThucDonDTO, Long id) throws UpdateDataFailException, DataNotFoundException {
        try{
            Optional<ThucDon> opt= thucDonRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("Thuc don khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_THUCDON_NOT_FOUND);
            }
            ThucDon thucDon= opt.get();
            thucDon.setTenThucDon(viewThucDonDTO.getTenThucDon());
            thucDon.setSetThucDon(viewThucDonDTO.getSetThucDon());
            thucDon.setDonGia(viewThucDonDTO.getDonGia());
            thucDon.setMacDinh(viewThucDonDTO.getMacDinh());
            Optional<HinhAnh> optHinhAnh= hinhAnhRepository.findById(viewThucDonDTO.getMaHinhAnh());
            if(!optHinhAnh.isPresent()){
                LOGGER.info("HinhAnh khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
            }
            HinhAnh hinhAnh= optHinhAnh.get();
            thucDon.setHinhAnhTD(hinhAnh);

            Optional<MonAn> optMonAn= monAnRepository.findById(viewThucDonDTO.getMaMonAn());
            if(!optMonAn.isPresent()){
                LOGGER.info("Mon an khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_MONAN_NOT_FOUND);
            }
            MonAn monAn= optMonAn.get();
            thucDon.setMonAnTD(monAn);

            return thucDonRepository.save(thucDon);

        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_MONAN_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_MONAN_NOT_FOUND);
            }else if(message.equals(ErrorCode.ERR_HINHANH_NOT_FOUND)){
                throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
            }else{
                throw new DataNotFoundException(ErrorCode.ERR_THUCDON_NOT_FOUND);
            }

        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_THUCDON_FAIL);
        }
    }

    @Override
    public ThucDon updateNhieuThucDon(UpdateNhieuThucDonDTO viewThucDonDTO, Long id) throws UpdateDataFailException, DataNotFoundException {
        try{
            Optional<ThucDon> opt= thucDonRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("Thuc don khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_THUCDON_NOT_FOUND);
            }
            ThucDon thucDon= opt.get();
            thucDon.setTenThucDon(viewThucDonDTO.getTenThucDon());
            thucDon.setSetThucDon(thucDon.getSetThucDon());
            thucDon.setDonGia(viewThucDonDTO.getDonGia());
            thucDon.setMacDinh(thucDon.getMacDinh());
            thucDon.setHinhAnhTD(thucDon.getHinhAnhTD());
            thucDon.setMonAnTD(thucDon.getMonAnTD());

            return thucDonRepository.save(thucDon);

        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_MONAN_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_MONAN_NOT_FOUND);
            }else if(message.equals(ErrorCode.ERR_HINHANH_NOT_FOUND)){
                throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
            }else{
                throw new DataNotFoundException(ErrorCode.ERR_THUCDON_NOT_FOUND);
            }

        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_THUCDON_FAIL);
        }
    }

    @Override
    public void deleteThucDon(Long id) throws DeleteDataFailException, DataNotFoundException {
        try{
            Optional<ThucDon> opt= thucDonRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("ThucDon khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_THUCDON_NOT_FOUND);
            }
            thucDonRepository.deleteById(id);
        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_THUCDON_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_THUCDON_NOT_FOUND);
            }
        }catch(Exception ex){
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_THUCDON_FAIL);
        }
    }

    @Override
    public ViewThucDonDTO getThucDonById(Long id) throws DataNotFoundException {
        ViewThucDonDTO viewThucDonDTO;
        try{
            Optional<ThucDon> optThucDon= thucDonRepository.findById(id);
            if(!optThucDon.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_THUCDON_NOT_FOUND);
            }
            ThucDon thucDon= optThucDon.get();
            viewThucDonDTO= thucDonConverter.convertToDTO(thucDon);

        }catch (Exception exception){
            throw new DataNotFoundException(ErrorCode.ERR_THUCDON_NOT_FOUND);
        }
        return viewThucDonDTO;
    }

    @Override
    public List<ViewThucDonDTO> getListThucDon() throws DataNotFoundException {
        List<ViewThucDonDTO> listDTO;
        try{
            List<ThucDon> listEntity= thucDonRepository.findAll();
            listDTO=thucDonConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_THUCDON_LIST_LOADED_FAIL);
        }
        return listDTO;
    }

    @Override
    public List<Long> getListMaMonAn(String tenThucDon) throws DataNotFoundException {
        List<Long> listMaMonAn;
        try{
            listMaMonAn= thucDonRepository.timMaMonAn(tenThucDon);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_THUCDON_LIST_LOADED_FAIL);
        }
        return listMaMonAn;
    }

    @Override
    public List<Long> getListMaThucDonTheoTenTD(String tenThucDon) throws DataNotFoundException {
        List<Long> listMaMonAn;
        try{
            listMaMonAn= thucDonRepository.getNhieuMaThucDonTheoTen(tenThucDon);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_THUCDON_LIST_LOADED_FAIL);
        }
        return listMaMonAn;
    }

    @Override
    public List<ViewThucDonDTO> getListThucDonKhongTrung() throws DataNotFoundException {
        List<ViewThucDonDTO> listDTO;
        try{
            List<ThucDon> listEntity= thucDonRepository.getThucDonKhongTrung();
            listDTO=thucDonConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_THUCDON_LIST_LOADED_FAIL);
        }
        return listDTO;
    }
}
