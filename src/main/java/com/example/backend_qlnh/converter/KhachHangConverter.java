package com.example.backend_qlnh.converter;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.dto.KhachHangDTO.CreateKhachHangDTO;
import com.example.backend_qlnh.dto.KhachHangDTO.ViewKhachHangDTO;
import com.example.backend_qlnh.entity.*;
import com.example.backend_qlnh.exception.ConvertEntityDTOException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.repository.HinhAnhRepository;
import com.example.backend_qlnh.repository.KhachHangRepository;
import com.example.backend_qlnh.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class KhachHangConverter {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    KhachHangRepository khachHangRepository;
    @Autowired
    HinhAnhRepository hinhAnhRepository;
    @Autowired
    UserRepository userRepository;

    public ViewKhachHangDTO convertToDTO(KhachHang khachHang){
        ViewKhachHangDTO dto= modelMapper.map(khachHang,ViewKhachHangDTO.class);
        if(khachHang.getHinhAnhKH()==null){
            dto.setMaHinhAnh(null);
        }else{
            Long maHA= khachHang.getHinhAnhKH().getMaHinhAnh();
            dto.setMaHinhAnh(maHA);
        }
        if(khachHang.getTaiKhoanKH()==null){
            dto.setMaTaiKhoan(null);
        }else{
            Long maTK= khachHang.getTaiKhoanKH().getId();
            dto.setMaTaiKhoan(maTK);
        }
        return dto;
    }

    public KhachHang convertToEntity(CreateKhachHangDTO createKhachHangDTO) throws ConvertEntityDTOException {
        try{
            KhachHang khachHang= modelMapper.map(createKhachHangDTO,KhachHang.class);

            if(createKhachHangDTO.getMaHinhAnh()==null){
                khachHang.setHinhAnhKH(null);

            }
            else{
                Optional<HinhAnh> opt= hinhAnhRepository.findById(createKhachHangDTO.getMaHinhAnh());
                HinhAnh hinhAnh;
                if(!opt.isPresent()){
                    throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
                }
                hinhAnh= opt.get();
                khachHang.setHinhAnhKH(hinhAnh);
            }

            if(createKhachHangDTO.getMaTaiKhoan()==null){
                khachHang.setTaiKhoanKH(null);
            }else{
                Optional<User> opt1= userRepository.findById(createKhachHangDTO.getMaTaiKhoan());
                User user;
                if(!opt1.isPresent()){
                    throw new DataNotFoundException(ErrorCode.ERR_TAIKHOAN_NOT_FOUND);
                }
                user = opt1.get();
                khachHang.setTaiKhoanKH(user);
            }
            return khachHang;

        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

    public List<ViewKhachHangDTO> convertToListDTO(List<KhachHang> listEntity) throws ConvertEntityDTOException {
        List<ViewKhachHangDTO> listDTO;
        try{
            listDTO=listEntity.stream().map(this::convertToDTO).collect(Collectors.toList());
        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
        return listDTO;
    }



}
