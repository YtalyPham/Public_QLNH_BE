package com.example.backend_qlnh.service.impl;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.converter.HinhAnhConverter;
import com.example.backend_qlnh.dto.HinhAnhDTO.CreateHinhAnhDTO;
import com.example.backend_qlnh.dto.HinhAnhDTO.ViewDetailHinhAnhDTO;
import com.example.backend_qlnh.entity.HinhAnh;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.repository.HinhAnhRepository;
import com.example.backend_qlnh.service.HinhAnhService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HinhAnhServiceImpl  implements HinhAnhService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HinhAnhServiceImpl.class);

    @Autowired
    private HinhAnhRepository hinhAnhRepository;

    @Autowired
    private HinhAnhConverter hinhAnhConverter;


    @Override
    public HinhAnh createHinhAnh(CreateHinhAnhDTO createHinhAnhDTO) throws CreateDataFailException {
        HinhAnh hinhAnh;
        try{
            hinhAnh=hinhAnhConverter.convertToEntity(createHinhAnhDTO);
            return hinhAnhRepository.save(hinhAnh);
        }catch (Exception ex){
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_HINHANH_FAIL);
        }
    }

    @Override
    public HinhAnh updateHinhAnh(ViewDetailHinhAnhDTO viewDetailHinhAnhDTO, Long id) throws UpdateDataFailException, DataNotFoundException {
        try{
            Optional<HinhAnh> opt= hinhAnhRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("Hinh anh khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
            }
            HinhAnh hinhAnh= opt.get();
            hinhAnh.setMaUrl(viewDetailHinhAnhDTO.getMaUrl());
            hinhAnh.setMaKey(viewDetailHinhAnhDTO.getMaKey());
            return hinhAnhRepository.save(hinhAnh);
        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_HINHANH_FAIL);
        }
    }

    @Override
    public void deleteHinhAnh(Long id) throws DeleteDataFailException, DataNotFoundException {
        try{
            Optional<HinhAnh> opt= hinhAnhRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("Hinh anh khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
            }
            hinhAnhRepository.deleteById(id);
        }catch(Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_UPDATE_HINHANH_FAIL);
        }
    }

    @Override
    public ViewDetailHinhAnhDTO getHinhAnhById(Long id) throws DataNotFoundException {
        ViewDetailHinhAnhDTO viewDetailHinhAnhDTO;
        try{
            Optional<HinhAnh> optHinhAnh= hinhAnhRepository.findById(id);
            if(!optHinhAnh.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
            }
            HinhAnh hinhAnh= optHinhAnh.get();
            viewDetailHinhAnhDTO= hinhAnhConverter.convertToDTO(hinhAnh);

        }catch (Exception exception){
            throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
        }
        return viewDetailHinhAnhDTO;

    }

    @Override
    public List<ViewDetailHinhAnhDTO> getListHinhAnh() throws DataNotFoundException {
        List<ViewDetailHinhAnhDTO> listHinhDTO;
        try{
            List<HinhAnh> listEntity= hinhAnhRepository.findAll();
            listHinhDTO=hinhAnhConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_HINHANH_LIST_NOT_FOUND);
        }
        return listHinhDTO;
    }




}
