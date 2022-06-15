package com.example.backend_qlnh.converter;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.dto.NhanVienDTO.CreateNhanVienDTO;
import com.example.backend_qlnh.dto.NhanVienDTO.ViewNhanVienDTO;
import com.example.backend_qlnh.entity.*;
import com.example.backend_qlnh.exception.ConvertEntityDTOException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.repository.HinhAnhRepository;
import com.example.backend_qlnh.repository.LoaiNhanVienRepository;
import com.example.backend_qlnh.repository.NhanVienRepository;
import com.example.backend_qlnh.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class NhanVienConverter {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    NhanVienRepository nhanVienRepository;
    @Autowired
    LoaiNhanVienRepository loaiNhanVienRepository;
    @Autowired
    HinhAnhRepository hinhAnhRepository;
    @Autowired
    UserRepository userRepository;

    public ViewNhanVienDTO convertToDTO(NhanVien nhanVien){
        ViewNhanVienDTO dto= modelMapper.map(nhanVien, ViewNhanVienDTO.class);
        Long maHA= nhanVien.getHinhAnhNV().getMaHinhAnh();
        Long maTK= nhanVien.getTaiKhoanNV().getId();
        Long maLoai= nhanVien.getLoaiNhanVienNV().getMaLoaiNhanVien();
        dto.setMaHinhAnh(maHA);
        dto.setMaTaiKhoan(maTK);
        dto.setMaLoaiNhanVien(maLoai);
        return dto;
    }

    public NhanVien convertToEntity(CreateNhanVienDTO createNhanVienDTO) throws ConvertEntityDTOException {
        try{
            NhanVien nhanVien= modelMapper.map(createNhanVienDTO,NhanVien.class);

            Optional<HinhAnh> opt= hinhAnhRepository.findById(createNhanVienDTO.getMaHinhAnh());
            HinhAnh hinhAnh;
            if(!opt.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
            }
            hinhAnh= opt.get();
            nhanVien.setHinhAnhNV(hinhAnh);

            Optional<User> opt1= userRepository.findById(createNhanVienDTO.getMaTaiKhoan());
            User user;
            if(!opt1.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_TAIKHOAN_NOT_FOUND);
            }
            user = opt1.get();
            nhanVien.setTaiKhoanNV(user);

            Optional<LoaiNhanVien> opt2= loaiNhanVienRepository.findById(createNhanVienDTO.getMaLoaiNhanVien());
            LoaiNhanVien loaiNhanVien;
            if(!opt2.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_LOAINHANVIEN_NOT_FOUND);
            }
            loaiNhanVien= opt2.get();
            nhanVien.setLoaiNhanVienNV(loaiNhanVien);

            return nhanVien;

        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
    }

    public List<ViewNhanVienDTO> convertToListDTO(List<NhanVien> listEntity) throws ConvertEntityDTOException {
        List<ViewNhanVienDTO> listDTO;
        try{
            listDTO=listEntity.stream().map(this::convertToDTO).collect(Collectors.toList());
        }catch (Exception ex){
            throw new ConvertEntityDTOException(ErrorCode.ERR_CONVERT_DTO_ENTITY_FAIL);
        }
        return listDTO;
    }
}
