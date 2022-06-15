package com.example.backend_qlnh.converter;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.dto.TaiSanDTO.CreateTaiSanDTO;
import com.example.backend_qlnh.dto.TaiSanDTO.ViewTaiSanDTO;
import com.example.backend_qlnh.entity.HinhAnh;
import com.example.backend_qlnh.entity.NhaHang;
import com.example.backend_qlnh.entity.TaiSan;
import com.example.backend_qlnh.exception.ConvertEntityDTOException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.repository.HinhAnhRepository;
import com.example.backend_qlnh.repository.NhaHangRepository;
import com.example.backend_qlnh.repository.TaiSanRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TaiSanConverter {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    TaiSanRepository taiSanRepository;
    @Autowired
    HinhAnhRepository hinhAnhRepository;
    @Autowired
    NhaHangRepository nhaHangRepository;

    public ViewTaiSanDTO convertToDTO(TaiSan taiSan){
        ViewTaiSanDTO dto= modelMapper.map(taiSan,ViewTaiSanDTO.class);
        Long maHA= taiSan.getHinhAnhTS().getMaHinhAnh();
        Long maNH= taiSan.getNhahang().getMaNhaHang();
        dto.setMaHinhAnh(maHA);
        dto.setMaNhahang(maNH);
        return dto;
    }

    public TaiSan convertToEntity(CreateTaiSanDTO createTaiSanDTO) throws ConvertEntityDTOException {
        try{
            TaiSan taiSan= modelMapper.map(createTaiSanDTO,TaiSan.class);

            Optional<HinhAnh> opt= hinhAnhRepository.findById(createTaiSanDTO.getMaHinhAnh());
            HinhAnh hinhAnh;
            if(!opt.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
            }
            hinhAnh= opt.get();
            taiSan.setHinhAnhTS(hinhAnh);

            Optional<NhaHang> opt1= nhaHangRepository.findById(createTaiSanDTO.getMaNhaHang());
            NhaHang nhaHang;
            if(!opt1.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_NHAHANG_NOT_FOUND);
            }
            nhaHang= opt1.get();
            taiSan.setNhahang(nhaHang);
            return taiSan;

        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

    public List<ViewTaiSanDTO> convertToListDTO(List<TaiSan> listEntity) throws ConvertEntityDTOException {
        List<ViewTaiSanDTO> listDTO;
        try{
            listDTO=listEntity.stream().map(this::convertToDTO).collect(Collectors.toList());
        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
        return listDTO;
    }


}
