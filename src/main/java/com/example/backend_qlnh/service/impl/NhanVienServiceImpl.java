package com.example.backend_qlnh.service.impl;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.converter.NhanVienConverter;
import com.example.backend_qlnh.dto.NhanVienDTO.CreateNhanVienDTO;
import com.example.backend_qlnh.dto.NhanVienDTO.ViewNhanVienDTO;
import com.example.backend_qlnh.entity.*;
import com.example.backend_qlnh.exception.CreateDataFailException;
import com.example.backend_qlnh.exception.DataNotFoundException;
import com.example.backend_qlnh.exception.DeleteDataFailException;
import com.example.backend_qlnh.exception.UpdateDataFailException;
import com.example.backend_qlnh.repository.HinhAnhRepository;
import com.example.backend_qlnh.repository.LoaiNhanVienRepository;
import com.example.backend_qlnh.repository.NhanVienRepository;
import com.example.backend_qlnh.repository.UserRepository;
import com.example.backend_qlnh.service.NhanVienService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NhanVienServiceImpl implements NhanVienService {
    @Autowired
    NhanVienRepository nhanVienRepository;
    @Autowired
    LoaiNhanVienRepository loaiNhanVienRepository;
    @Autowired
    HinhAnhRepository hinhAnhRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    NhanVienConverter nhanVienConverter;


    private static final Logger LOGGER = LoggerFactory.getLogger(NhanVienServiceImpl.class);
    @Override
    public NhanVien createNhanVien(CreateNhanVienDTO createNhanVienDTO) throws CreateDataFailException {
        NhanVien nhanVien;
        try{
            nhanVien=nhanVienConverter.convertToEntity(createNhanVienDTO);
            nhanVien.setNgayNghi(0);
            nhanVien.setNgayLam(0);
            return nhanVienRepository.save(nhanVien);
        }catch (Exception ex){
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_NHANVIEN_FAIL);
        }
    }

    @Override
    public NhanVien updateNhanVien(ViewNhanVienDTO viewDetailNhanVienDTO, Long id) throws UpdateDataFailException, DataNotFoundException {
        try{
            Optional<NhanVien> opt= nhanVienRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("NhanVien khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_NHANVIEN_NOT_FOUND);
            }
            NhanVien nhanVien= opt.get();
            nhanVien.setTenNhanVien(viewDetailNhanVienDTO.getTenNhanVien());
            nhanVien.setNgayNghi(viewDetailNhanVienDTO.getNgayNghi());
            nhanVien.setNgayLam(viewDetailNhanVienDTO.getNgayLam());
            nhanVien.setLuongCoBan(viewDetailNhanVienDTO.getLuongCoBan());
            nhanVien.setCmnd(viewDetailNhanVienDTO.getCmnd());
            nhanVien.setDiaChi(viewDetailNhanVienDTO.getDiaChi());
            nhanVien.setSdt(viewDetailNhanVienDTO.getSdt());
            nhanVien.setNgaySinh(viewDetailNhanVienDTO.getNgaySinh());
            nhanVien.setNgayThue(viewDetailNhanVienDTO.getNgayThue());
            nhanVien.setGioiTinh(viewDetailNhanVienDTO.isGioiTinh());
            nhanVien.setEmail(viewDetailNhanVienDTO.getEmail());

            Optional<HinhAnh> optHinhAnh= hinhAnhRepository.findById(viewDetailNhanVienDTO.getMaHinhAnh());
            if(!optHinhAnh.isPresent()){
                LOGGER.info("HinhAnh khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
            }
            HinhAnh hinhAnh= optHinhAnh.get();
            nhanVien.setHinhAnhNV(hinhAnh);

            Optional<User> optTaiKhoan= userRepository.findById(viewDetailNhanVienDTO.getMaTaiKhoan());
            if(!optTaiKhoan.isPresent()){
                LOGGER.info("TaiKhoan khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_TAIKHOAN_NOT_FOUND);
            }
            User user = optTaiKhoan.get();
            nhanVien.setTaiKhoanNV(user);

            Optional<LoaiNhanVien> optL= loaiNhanVienRepository.findById(viewDetailNhanVienDTO.getMaLoaiNhanVien());
            if(!optL.isPresent()){
                LOGGER.info("LoaiNhanVien khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_LOAINHANVIEN_NOT_FOUND);
            }
            LoaiNhanVien loaiNhanVien= optL.get();
            nhanVien.setLoaiNhanVienNV(loaiNhanVien);

            return nhanVienRepository.save(nhanVien);
        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_TAIKHOAN_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_TAIKHOAN_NOT_FOUND);
            }else if(message.equals(ErrorCode.ERR_HINHANH_NOT_FOUND)){
                throw new DataNotFoundException(ErrorCode.ERR_HINHANH_NOT_FOUND);
            }else if(message.equals(ErrorCode.ERR_LOAINHANVIEN_NOT_FOUND)){
                throw new DataNotFoundException(ErrorCode.ERR_LOAINHANVIEN_NOT_FOUND);
            }else{
                throw new DataNotFoundException(ErrorCode.ERR_NHANVIEN_NOT_FOUND);
            }

        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_NHANVIEN_FAIL);
        }
    }

    @Override
    public void deleteNhanVien(Long id) throws DeleteDataFailException, DataNotFoundException {
        try{
            Optional<NhanVien> opt= nhanVienRepository.findById(id);
            if(!opt.isPresent()){
                LOGGER.info("NhanVien khong tim thay: {}",id);
                throw new DataNotFoundException(ErrorCode.ERR_NHANVIEN_NOT_FOUND);
            }
            nhanVienRepository.deleteById(id);
        }catch (DataNotFoundException e) {
            String message = e.getMessage();
            if (message.equals(ErrorCode.ERR_NHANVIEN_NOT_FOUND)) {
                throw new DataNotFoundException(ErrorCode.ERR_NHANVIEN_NOT_FOUND);
            }
        }catch(Exception ex){
            throw new DeleteDataFailException(ErrorCode.ERR_DELETE_NHANVIEN_FAIL);
        }
    }

    @Override
    public ViewNhanVienDTO getNhanVienById(Long id) throws DataNotFoundException {
        ViewNhanVienDTO viewNhanVienDTO;
        try{
            Optional<NhanVien> optNhanVien= nhanVienRepository.findById(id);
            if(!optNhanVien.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_NHANVIEN_NOT_FOUND);
            }
            NhanVien nhanVien= optNhanVien.get();
            viewNhanVienDTO= nhanVienConverter.convertToDTO(nhanVien);

        }catch (Exception exception){
            throw new DataNotFoundException(ErrorCode.ERR_NHANVIEN_NOT_FOUND);
        }
        return viewNhanVienDTO;
    }

    @Override
    public List<ViewNhanVienDTO> getListNhanVien() throws DataNotFoundException {
        List<ViewNhanVienDTO> listDTO;
        try{
            List<NhanVien> listEntity= nhanVienRepository.findAll();
            listDTO=nhanVienConverter.convertToListDTO(listEntity);
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_NHANVIEN_LIST_LOADED_FAIL);
        }
        return listDTO;
    }

    @Override
    public int diemDanh(Long id) throws DataNotFoundException {
         NhanVien nhanVien;
         try{
             Optional<NhanVien> optNhanVien= nhanVienRepository.findById(id);
             if(!optNhanVien.isPresent()){
                 throw new DataNotFoundException(ErrorCode.ERR_NHANVIEN_NOT_FOUND);
             }
             nhanVien= optNhanVien.get();
             int ngaydiemdanh= nhanVien.getNgayLam()+1;
             nhanVien.setNgayLam(ngaydiemdanh);
             nhanVienRepository.save(nhanVien);
         }catch(Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_NHANVIEN_NOT_FOUND);
        }
         return nhanVien.getNgayLam();
    }

    @Override
    public ViewNhanVienDTO getNVByMaTK(Long id) throws DataNotFoundException {
        ViewNhanVienDTO viewNhanVienDTO;
        try{
            Optional<NhanVien> optNhanVien= nhanVienRepository.getNVTheoMaTK(id);
            if(!optNhanVien.isPresent()){
                throw new DataNotFoundException(ErrorCode.ERR_NHANVIEN_NOT_FOUND);
            }
            NhanVien nhanVien= optNhanVien.get();
            viewNhanVienDTO= nhanVienConverter.convertToDTO(nhanVien);

        }catch (Exception exception){
            throw new DataNotFoundException(ErrorCode.ERR_NHANVIEN_NOT_FOUND);
        }
        return viewNhanVienDTO;
    }

    @Override
    public Integer demslNhanVien() throws DataNotFoundException {
        Integer sl;
        try{
            sl= nhanVienRepository.demSlNhanVien();
        }catch (Exception ex){
            throw new DataNotFoundException(ErrorCode.ERR_NHANVIEN_NOT_FOUND);
        }
        return sl;
    }
}
