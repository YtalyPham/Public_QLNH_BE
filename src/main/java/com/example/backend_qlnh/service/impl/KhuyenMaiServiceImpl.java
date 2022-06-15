package com.example.backend_qlnh.service.impl;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.converter.KhuyenMaiConverter;
import com.example.backend_qlnh.dto.KhuyenMaiDTO.CreateKhuyenMaiDTO;
import com.example.backend_qlnh.dto.KhuyenMaiDTO.ViewDetailKhuyenMaiDTO;
import com.example.backend_qlnh.dto.TaiSanDTO.ViewTaiSanDTO;
import com.example.backend_qlnh.entity.HinhAnh;
import com.example.backend_qlnh.entity.KhuyenMai;
import com.example.backend_qlnh.entity.NhaHang;
import com.example.backend_qlnh.entity.TaiSan;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.repository.HinhAnhRepository;
import com.example.backend_qlnh.repository.KhuyenMaiRepository;
import com.example.backend_qlnh.service.KhuyenMaiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KhuyenMaiServiceImpl implements KhuyenMaiService {
    @Autowired
    KhuyenMaiConverter khuyenMaiConverter;
    @Autowired
    KhuyenMaiRepository khuyenMaiRepository;
    @Autowired
    HinhAnhRepository hinhAnhRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(KhuyenMaiServiceImpl.class);

    @Override
    public KhuyenMai createKhuyenMai(CreateKhuyenMaiDTO createKhuyenMaiDTO) throws CreateDataFailException {
        KhuyenMai khuyenMai;
        try{
            khuyenMai=khuyenMaiConverter.convertToEntity(createKhuyenMaiDTO);
            return khuyenMaiRepository.save(khuyenMai);
        }catch (Exception ex){
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_KHUYENMAI_FAIL);
        }
    }

    @Override
    public KhuyenMai updateKhuyenMai(ViewDetailKhuyenMaiDTO viewDetailKhuyenMaiDTO, Long id) throws UpdateDataFailException, DataNotFoundException {
        try{
            Optional<KhuyenMai> opt= khuyenMaiRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("KhuyenMai khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_KHUYENMAI_NOT_FOUND);
            }
            KhuyenMai khuyenMai= opt.get();
            khuyenMai.setTenKhuyenMai(viewDetailKhuyenMaiDTO.getTenKhuyenMai());
            khuyenMai.setNoiDung(viewDetailKhuyenMaiDTO.getNoiDung());
            khuyenMai.setGiamGia(viewDetailKhuyenMaiDTO.getGiamGia());
            khuyenMai.setThoiGianKetThuc(viewDetailKhuyenMaiDTO.getThoiGianKetThuc());
            khuyenMai.setThoiGianBatDau(viewDetailKhuyenMaiDTO.getThoiGianBatDau());

            Optional<HinhAnh> optHinhAnh= hinhAnhRepository.findById(viewDetailKhuyenMaiDTO.getMaHinhAnh());
            if(!optHinhAnh.isPresent()){
                LOGGER.info("HinhAnh khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
            }
            HinhAnh hinhAnh= optHinhAnh.get();
            khuyenMai.setHinhAnhKM(hinhAnh);

            return khuyenMaiRepository.save(khuyenMai);
        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_KHUYENMAI_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_KHUYENMAI_NOT_FOUND);
            }else{
                throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
            }

        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_HINHANH_FAIL);
        }
    }

    @Override
    public void deleteKhuyenMai(Long id) throws DeleteDataFailException, DataNotFoundException {
        try{
            Optional<KhuyenMai> opt= khuyenMaiRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("KhuyenMai khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_KHUYENMAI_NOT_FOUND);
            }
            khuyenMaiRepository.deleteById(id);
        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_KHUYENMAI_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_KHUYENMAI_NOT_FOUND);
            }
        }catch(Exception ex){
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_KHUYENMAI_FAIL);
        }
    }

    @Override
    public ViewDetailKhuyenMaiDTO getKhuyenMaiById(Long id) throws DataNotFoundException {
        ViewDetailKhuyenMaiDTO viewDetailKhuyenMaiDTO;
        try{
            Optional<KhuyenMai> opt= khuyenMaiRepository.findById(id);
            if(!opt.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_KHUYENMAI_NOT_FOUND);
            }
            KhuyenMai khuyenMai= opt.get();
            viewDetailKhuyenMaiDTO= khuyenMaiConverter.convertToDTO(khuyenMai);

        }catch (Exception exception){
            throw new DataNotFoundException(ErrorCode.ERR_KHUYENMAI_NOT_FOUND);
        }
        return viewDetailKhuyenMaiDTO;
    }

    @Override
    public List<ViewDetailKhuyenMaiDTO> getListKhuyenMai() throws DataNotFoundException {
        List<ViewDetailKhuyenMaiDTO> listDTO;
        try{
            List<KhuyenMai> listEntity= khuyenMaiRepository.findAll();
            listDTO=khuyenMaiConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_KHUYENMAI_LIST_LOADED_FAIL);
        }
        return listDTO;
    }
}
