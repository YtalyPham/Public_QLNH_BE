package com.example.backend_qlnh.service.impl;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.converter.NhaHangConverter;
import com.example.backend_qlnh.dto.HinhAnhDTO.ViewDetailHinhAnhDTO;
import com.example.backend_qlnh.dto.NhaHangDTO.CreateNhaHangDTO;
import com.example.backend_qlnh.dto.NhaHangDTO.ViewNhaHangDTO;
import com.example.backend_qlnh.entity.HinhAnh;
import com.example.backend_qlnh.entity.NhaHang;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.repository.HinhAnhRepository;
import com.example.backend_qlnh.repository.NhaHangRepository;
import com.example.backend_qlnh.service.NhaHangService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NhaHangServiceImpl implements NhaHangService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NhaHangServiceImpl.class);

    @Autowired
    private NhaHangRepository nhaHangRepository;

    @Autowired
    private NhaHangConverter nhaHangConverter;

    @Autowired
    private HinhAnhRepository hinhAnhRepository;

    @Override
    public NhaHang createNhaHang(CreateNhaHangDTO createNhaHangDTO) throws CreateDataFailException {
        NhaHang nhaHang;
        try{
            nhaHang=nhaHangConverter.convertToEntity(createNhaHangDTO);
            return nhaHangRepository.save(nhaHang);
        }catch (Exception ex){
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_NHAHANG_FAIL);
        }
    }

    @Override
    public NhaHang updateNhaHang(ViewNhaHangDTO viewDetailNhaHangDTO, Long id) throws UpdateDataFailException, DataNotFoundException {
        try{
            Optional<NhaHang> opt= nhaHangRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("Nha hang khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_NHAHANG_NOT_FOUND);
            }
            NhaHang nhaHang= opt.get();
            nhaHang.setTenNhaHang(viewDetailNhaHangDTO.getTenNhaHang());
            nhaHang.setDienTich(viewDetailNhaHangDTO.getDienTich());
            nhaHang.setEmail(viewDetailNhaHangDTO.getEmail());
            nhaHang.setSdt(viewDetailNhaHangDTO.getSdt());
            nhaHang.setDiaChi(viewDetailNhaHangDTO.getDiaChi());

            Optional<HinhAnh> optHinhAnh= hinhAnhRepository.findById(viewDetailNhaHangDTO.getMaHinhAnh());
            if(!optHinhAnh.isPresent()){
                LOGGER.info("HinhAnh khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
            }
            HinhAnh hinhAnh= optHinhAnh.get();
            nhaHang.setHinhAnhNH(hinhAnh);

            return nhaHangRepository.save(nhaHang);
        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_HINHANH_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
            }else {
                throw new DataNotFoundException(ErrorCode.ERR_NHAHANG_NOT_FOUND);
            }

        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_HINHANH_FAIL);
        }
    }

    @Override
    public void deleteNhaHang(Long id) throws DeleteDataFailException, DataNotFoundException {
        try{
            Optional<NhaHang> opt= nhaHangRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("NhaHang khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_NHAHANG_NOT_FOUND);
            }
            nhaHangRepository.deleteById(id);
        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_NHAHANG_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_NHAHANG_NOT_FOUND);
            }
        }catch(Exception ex){
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_NHAHANG_FAIL);
        }
    }

    @Override
    public ViewNhaHangDTO getNhaHangById(Long id) throws DataNotFoundException {
        ViewNhaHangDTO viewNhaHangDTO;
        try{
            Optional<NhaHang> optNhaHang= nhaHangRepository.findById(id);
            if(!optNhaHang.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_NHAHANG_NOT_FOUND);
            }
            NhaHang nhaHang= optNhaHang.get();
            viewNhaHangDTO= nhaHangConverter.convertToDTO(nhaHang);

        }catch (Exception exception){
            throw new DataNotFoundException(ErrorCode.ERR_NHAHANG_NOT_FOUND);
        }
        return viewNhaHangDTO;
    }

    @Override
    public List<ViewNhaHangDTO> getListNhaHang() throws DataNotFoundException {
        List<ViewNhaHangDTO> listDTO;
        try{
            List<NhaHang> listEntity= nhaHangRepository.findAll();
            listDTO=nhaHangConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_NHAHANG_LIST_LOADED_FAIL);
        }
        return listDTO;
    }
}
