package com.example.backend_qlnh.constants;

public class ErrorCode {
    /** CONVERTER **/
    public static final String ERR_CONVERT_DTO_ENTITY_FAIL = "ERR_CONVERT_DTO_ENTITY_FAIL"; // Use in converter package

    /** AUTHENTICATION - AUTHORIZATION **/
    public static final String ERR_KHACHHANG_LOGIN_FAIL = "ERR_KHACHHANG_LOGIN_FAIL"; // Use in KHACHHANG login
    public static final String ERR_KHACHHANG_CHANGE_PASSWORD_FAIL = "ERR_KHACHHANG_CHANGE_PASSWORD_FAIL"; // Use in KHACHHANG login
    public static final String ERR_NHANVIEN_LOGIN_FAIL = "ERR_NHANVIEN_LOGIN_FAIL"; // Use in KHACHHANG login
    public static final String ERR_NHANVIEN_CHANGE_PASSWORD_FAIL = "ERR_NHANVIEN_CHANGE_PASSWORD_FAIL"; // Use in KHACHHANG login


    /** KHACHHANG **/
    public static final String ERR_KHACHHANG_NOT_FOUND = "ERR_KHACHHANG_NOT_FOUND"; // Use in update, delete, getDetails KHACHHANG service of Admin, get details + Staff
    public static final String ERR_KHACHHANG_LOADED_FAIL = "ERR_KHACHHANG_LOADED_FAIL"; // Use in GET load KHACHHANG controller of Admin, Staff
    public static final String ERR_KHACHHANG_LIST_NOT_FOUND = "ERR_KHACHHANG_LIST_NOT_FOUND"; // Use in get list all KHACHHANG service of Admin
    public static final String ERR_KHACHHANG_LIST_LOADED_FAIL = "ERR_KHACHHANG_LIST_LOADED_FAIL"; // Use in GET load KHACHHANG list controller of Admin
    public static final String ERR_KHACHHANG_LIST_EMPTY = "ERR_KHACHHANG_LIST_EMPTY";
    public static final String ERR_KHACHHANG_INFORMATION_DUPLICATE = "ERR_KHACHHANG_INFORMATION_DUPLICATE"; // Use in create KHACHHANG service of Admin
    public static final String ERR_CREATE_KHACHHANG_FAIL = "ERR_CREATE_KHACHHANG_FAIL"; // Use in POST create KHACHHANG controller of Admin
    public static final String ERR_UPDATE_KHACHHANG_FAIL = "ERR_UPDATE_KHACHHANG_FAIL"; // Use in PUT update KHACHHANG controller of Admin
    public static final String ERR_DELETE_KHACHHANG_FAIL = "ERR_DELETE_KHACHHANG_FAIL"; // Use in PUT delete KHACHHANG controller of Admin
    public static final String ERR_UPDATE_PASSWORD_KHACHHANG_FAIL = "ERR_UPDATE_PASSWORD_KHACHHANG_FAIL"; // Use in PUT update password KHACHHANG controller of KHACHHANG
    public static final String ERR_UPDATE_JOINDATE_KHACHHANG_FAIL = "ERR_UPDATE_JOINDATE_KHACHHANG_FAIL";
    public static final String ERR_UPDATE_DOB_KHACHHANG_FAIL = "ERR_UPDATE_DOB_KHACHHANG_FAIL";


    /** NHANVIEN **/
    public static final String ERR_NHANVIEN_NOT_FOUND = "ERR_NHANVIEN_NOT_FOUND"; // Use in update, delete, getDetails NHANVIEN service of Admin, get details + Staff
    public static final String ERR_NHANVIEN_LOADED_FAIL = "ERR_NHANVIEN_LOADED_FAIL"; // Use in GET load NHANVIEN controller of Admin, Staff
    public static final String ERR_NHANVIEN_LIST_NOT_FOUND = "ERR_NHANVIEN_LIST_NOT_FOUND"; // Use in get list all NHANVIEN service of Admin
    public static final String ERR_NHANVIEN_LIST_LOADED_FAIL = "ERR_NHANVIEN_LIST_LOADED_FAIL"; // Use in GET load NHANVIEN list controller of Admin
    public static final String ERR_NHANVIEN_LIST_EMPTY = "ERR_NHANVIEN_LIST_EMPTY";
    public static final String ERR_NHANVIEN_INFORMATION_DUPLICATE = "ERR_NHANVIEN_INFORMATION_DUPLICATE"; // Use in create NHANVIEN service of Admin
    public static final String ERR_CREATE_NHANVIEN_FAIL = "ERR_CREATE_NHANVIEN_FAIL"; // Use in POST create NHANVIEN controller of Admin
    public static final String ERR_UPDATE_NHANVIEN_FAIL = "ERR_UPDATE_NHANVIEN_FAIL"; // Use in PUT update NHANVIEN controller of Admin
    public static final String ERR_DELETE_NHANVIEN_FAIL = "ERR_DELETE_NHANVIEN_FAIL"; // Use in PUT delete NHANVIEN controller of Admin
    public static final String ERR_UPDATE_PASSWORD_NHANVIEN_FAIL = "ERR_UPDATE_PASSWORD_NHANVIEN_FAIL"; // Use in PUT update password NHANVIEN controller of NHANVIEN
    public static final String ERR_UPDATE_DOB_NHANVIEN_FAIL = "ERR_UPDATE_DOB_NHANVIEN_FAIL";
    public static final String ERR_WRONG_OLD_PASSWORD = "ERR_WRONG_OLD_PASSWORD";
    public static final String ERR_DIEMDANH = "ERR_DIEMDANH";


    /** VAITRO **/
    public static final String ERR_VAITRO_NOT_FOUND = "ERR_VAITRO_NOT_FOUND";
    public static final String ERR_VAITRO_LOADED_FAIL = "ERR_VAITRO_LOADED_FAIL";
    public static final String ERR_VAITRO_LIST_NOT_FOUND = "ERR_VAITRO_LIST_NOT_FOUND";
    public static final String ERR_VAITRO_LIST_LOADED_FAIL = "ERR_VAITRO_LIST_LOADED_FAIL";

    /** DICHVU **/
    public static final String ERR_DICHVU_NOT_FOUND = "ERR_DICHVU_NOT_FOUND";
    public static final String ERR_CREATE_DICHVU_FAIL = "ERR_CREATE_DICHVU_FAIL";
    public static final String ERR_UPDATE_DICHVU_FAIL = "ERR_UPDATE_DICHVU_FAIL";
    public static final String ERR_DELETE_DICHVU_FAIL = "ERR_DELETE_DICHVU_FAIL";
    public static final String ERR_DICHVU_LOADED_FAIL = "ERR_DICHVU_LOADED_FAIL";
    public static final String ERR_DICHVU_LIST_NOT_FOUND = "ERR_DICHVU_LIST_NOT_FOUND";
    public static final String ERR_DICHVU_LIST_LOADED_FAIL = "ERR_DICHVU_LIST_LOADED_FAIL";

    /** NHAHANG **/
    public static final String ERR_NHAHANG_NOT_FOUND = "ERR_NHAHANG_NOT_FOUND";
    public static final String ERR_CREATE_NHAHANG_FAIL = "ERR_CREATE_NHAHANG_FAIL";
    public static final String ERR_UPDATE_NHAHANG_FAIL = "ERR_UPDATE_NHAHANG_FAIL";
    public static final String ERR_DELETE_NHAHANG_FAIL = "ERR_DELETE_NHAHANG_FAIL";
    public static final String ERR_NHAHANG_LOADED_FAIL = "ERR_NHAHANG_LOADED_FAIL";
    public static final String ERR_NHAHANG_LIST_NOT_FOUND = "ERR_NHAHANG_LIST_NOT_FOUND";
    public static final String ERR_NHAHANG_LIST_LOADED_FAIL = "ERR_NHAHANG_LIST_LOADED_FAIL";

    /** DANHGIA **/
    public static final String ERR_DANHGIA_NOT_FOUND = "ERR_DANHGIA_NOT_FOUND";
    public static final String ERR_CREATE_DANHGIA_FAIL = "ERR_CREATE_DANHGIA_FAIL";
    public static final String ERR_UPDATE_DANHGIA_FAIL = "ERR_UPDATE_DANHGIA_FAIL";
    public static final String ERR_DELETE_DANHGIA_FAIL = "ERR_DELETE_DANHGIA_FAIL";
    public static final String ERR_DANHGIA_LOADED_FAIL = "ERR_DANHGIA_LOADED_FAIL";
    public static final String ERR_DANHGIA_LIST_NOT_FOUND = "ERR_DANHGIA_LIST_NOT_FOUND";
    public static final String ERR_DANHGIA_LIST_LOADED_FAIL = "ERR_DANHGIA_LIST_LOADED_FAIL";

    /** HINHANH **/
    public static final String ERR_HINHANH_NOT_FOUND = "ERR_HINHANH_NOT_FOUND";
    public static final String ERR_CREATE_HINHANH_FAIL = "ERR_CREATE_HINHANH_FAIL";
    public static final String ERR_UPDATE_HINHANH_FAIL = "ERR_UPDATE_HINHANH_FAIL";
    public static final String ERR_DELETE_HINHANH_FAIL = "ERR_DELETE_HINHANH_FAIL";
    public static final String ERR_HINHANH_LOADED_FAIL = "ERR_HINHANH_LOADED_FAIL";
    public static final String ERR_HINHANH_LIST_NOT_FOUND = "ERR_HINHANH_LIST_NOT_FOUND";
    public static final String ERR_HINHANH_LIST_LOADED_FAIL = "ERR_HINHANH_LIST_LOADED_FAIL";

    /** KHUYENMAI **/
    public static final String ERR_KHUYENMAI_NOT_FOUND = "ERR_KHUYENMAI_NOT_FOUND";
    public static final String ERR_CREATE_KHUYENMAI_FAIL = "ERR_CREATE_KHUYENMAI_FAIL";
    public static final String ERR_UPDATE_KHUYENMAI_FAIL = "ERR_UPDATE_KHUYENMAI_FAIL";
    public static final String ERR_DELETE_KHUYENMAI_FAIL = "ERR_DELETE_KHUYENMAI_FAIL";
    public static final String ERR_KHUYENMAI_LOADED_FAIL = "ERR_KHUYENMAI_LOADED_FAIL";
    public static final String ERR_KHUYENMAI_LIST_NOT_FOUND = "ERR_KHUYENMAI_LIST_NOT_FOUND";
    public static final String ERR_KHUYENMAI_LIST_LOADED_FAIL = "ERR_KHUYENMAI_LIST_LOADED_FAIL";

    /** MONAN **/
    public static final String ERR_MONAN_NOT_FOUND = "ERR_MONAN_NOT_FOUND";
    public static final String ERR_CREATE_MONAN_FAIL = "ERR_CREATE_MONAN_FAIL";
    public static final String ERR_UPDATE_MONAN_FAIL = "ERR_UPDATE_MONAN_FAIL";
    public static final String ERR_DELETE_MONAN_FAIL = "ERR_DELETE_MONAN_FAIL";
    public static final String ERR_MONAN_LOADED_FAIL = "ERR_MONAN_LOADED_FAIL";
    public static final String ERR_MONAN_LIST_NOT_FOUND = "ERR_MONAN_LIST_NOT_FOUND";
    public static final String ERR_MONAN_LIST_LOADED_FAIL = "ERR_MONAN_LIST_LOADED_FAIL";

    /** THUCDON **/
    public static final String ERR_THUCDON_NOT_FOUND = "ERR_THUCDON_NOT_FOUND";
    public static final String ERR_CREATE_THUCDON_FAIL = "ERR_CREATE_THUCDON_FAIL";
    public static final String ERR_UPDATE_THUCDON_FAIL = "ERR_UPDATE_THUCDON_FAIL";
    public static final String ERR_DELETE_THUCDON_FAIL = "ERR_DELETE_THUCDON_FAIL";
    public static final String ERR_THUCDON_LOADED_FAIL = "ERR_THUCDON_LOADED_FAIL";
    public static final String ERR_THUCDON_LIST_NOT_FOUND = "ERR_THUCDON_LIST_NOT_FOUND";
    public static final String ERR_THUCDON_LIST_LOADED_FAIL = "ERR_THUCDON_LIST_LOADED_FAIL";

    /** TAPDICHVU **/
    public static final String ERR_TAPDICHVU_NOT_FOUND = "ERR_TAPDICHVU_NOT_FOUND";
    public static final String ERR_CREATE_TAPDICHVU_FAIL = "ERR_CREATE_TAPDICHVU_FAIL";
    public static final String ERR_UPDATE_TAPDICHVU_FAIL = "ERR_UPDATE_TAPDICHVU_FAIL";
    public static final String ERR_DELETE_TAPDICHVU_FAIL = "ERR_DELETE_TAPDICHVU_FAIL";
    public static final String ERR_TAPDICHVU_LOADED_FAIL = "ERR_TAPDICHVU_LOADED_FAIL";
    public static final String ERR_TAPDICHVU_LIST_NOT_FOUND = "ERR_TAPDICHVU_LIST_NOT_FOUND";
    public static final String ERR_TAPDICHVU_LIST_LOADED_FAIL = "ERR_TAPDICHVU_LIST_LOADED_FAIL";

    /** NGUYENLIEU **/
    public static final String ERR_NGUYENLIEU_NOT_FOUND = "ERR_NGUYENLIEU_NOT_FOUND";
    public static final String ERR_CREATE_NGUYENLIEU_FAIL = "ERR_CREATE_NGUYENLIEU_FAIL";
    public static final String ERR_UPDATE_NGUYENLIEU_FAIL = "ERR_UPDATE_NGUYENLIEU_FAIL";
    public static final String ERR_DELETE_NGUYENLIEU_FAIL = "ERR_DELETE_NGUYENLIEU_FAIL";
    public static final String ERR_NGUYENLIEU_LOADED_FAIL = "ERR_NGUYENLIEU_LOADED_FAIL";
    public static final String ERR_NGUYENLIEU_LIST_NOT_FOUND = "ERR_NGUYENLIEU_LIST_NOT_FOUND";
    public static final String ERR_NGUYENLIEU_LIST_LOADED_FAIL = "ERR_NGUYENLIEU_LIST_LOADED_FAIL";

    /** SANHTIEC **/
    public static final String ERR_SANHTIEC_NOT_FOUND = "ERR_SANHTIEC_NOT_FOUND";
    public static final String ERR_CREATE_SANHTIEC_FAIL = "ERR_CREATE_SANHTIEC_FAIL";
    public static final String ERR_UPDATE_SANHTIEC_FAIL = "ERR_UPDATE_SANHTIEC_FAIL";
    public static final String ERR_DELETE_SANHTIEC_FAIL = "ERR_DELETE_SANHTIEC_FAIL";
    public static final String ERR_SANHTIEC_LOADED_FAIL = "ERR_SANHTIEC_LOADED_FAIL";
    public static final String ERR_SANHTIEC_LIST_NOT_FOUND = "ERR_SANHTIEC_LIST_NOT_FOUND";
    public static final String ERR_SANHTIEC_LIST_LOADED_FAIL = "ERR_SANHTIEC_LIST_LOADED_FAIL";

    /** TAISAN **/
    public static final String ERR_TAISAN_NOT_FOUND = "ERR_TAISAN_NOT_FOUND";
    public static final String ERR_CREATE_TAISAN_FAIL = "ERR_CREATE_TAISAN_FAIL";
    public static final String ERR_UPDATE_TAISAN_FAIL = "ERR_UPDATE_TAISAN_FAIL";
    public static final String ERR_DELETE_TAISAN_FAIL = "ERR_DELETE_TAISAN_FAIL";
    public static final String ERR_TAISAN_LOADED_FAIL = "ERR_TAISAN_LOADED_FAIL";
    public static final String ERR_TAISAN_LIST_NOT_FOUND = "ERR_TAISAN_LIST_NOT_FOUND";
    public static final String ERR_TAISAN_LIST_LOADED_FAIL = "ERR_TAISAN_LIST_LOADED_FAIL";

    /** PHIEUDATTIEC **/
    public static final String ERR_PHIEUDATTIEC_NOT_FOUND = "ERR_PHIEUDATTIEC_NOT_FOUND";
    public static final String ERR_CREATE_PHIEUDATTIEC_FAIL = "ERR_CREATE_PHIEUDATTIEC_FAIL";
    public static final String ERR_UPDATE_PHIEUDATTIEC_FAIL = "ERR_UPDATE_PHIEUDATTIEC_FAIL";
    public static final String ERR_DELETE_PHIEUDATTIEC_FAIL = "ERR_DELETE_PHIEUDATTIEC_FAIL";
    public static final String ERR_PHIEUDATTIEC_LOADED_FAIL = "ERR_PHIEUDATTIEC_LOADED_FAIL";
    public static final String ERR_PHIEUDATTIEC_LIST_NOT_FOUND = "ERR_PHIEUDATTIEC_LIST_NOT_FOUND";
    public static final String ERR_PHIEUDATTIEC_LIST_LOADED_FAIL = "ERR_PHIEUDATTIEC_LIST_LOADED_FAIL";

    /** CTHD **/
    public static final String ERR_CTHD_NOT_FOUND = "ERR_CTHD_NOT_FOUND";
    public static final String ERR_CREATE_CTHD_FAIL = "ERR_CREATE_CTHD_FAIL";
    public static final String ERR_UPDATE_CTHD_FAIL = "ERR_UPDATE_CTHD_FAIL";
    public static final String ERR_DELETE_CTHD_FAIL = "ERR_DELETE_CTHD_FAIL";
    public static final String ERR_CTHD_LOADED_FAIL = "ERR_CTHD_LOADED_FAIL";
    public static final String ERR_CTHD_LIST_NOT_FOUND = "ERR_CTHD_LIST_NOT_FOUND";
    public static final String ERR_CTHD_LIST_LOADED_FAIL = "ERR_CTHD_LIST_LOADED_FAIL";

    /** HOADON **/
    public static final String ERR_HOADON_NOT_FOUND = "ERR_HOADON_NOT_FOUND";
    public static final String ERR_CREATE_HOADON_FAIL = "ERR_CREATE_HOADON_FAIL";
    public static final String ERR_UPDATE_HOADON_FAIL = "ERR_UPDATE_HOADON_FAIL";
    public static final String ERR_DELETE_HOADON_FAIL = "ERR_DELETE_HOADON_FAIL";
    public static final String ERR_HOADON_LOADED_FAIL = "ERR_HOADON_LOADED_FAIL";
    public static final String ERR_HOADON_LIST_NOT_FOUND = "ERR_HOADON_LIST_NOT_FOUND";
    public static final String ERR_HOADON_LIST_LOADED_FAIL = "ERR_HOADON_LIST_LOADED_FAIL";

    /** LOAINHANVIEN **/
    public static final String ERR_LOAINHANVIEN_NOT_FOUND = "ERR_LOAINHANVIEN_NOT_FOUND";
    public static final String ERR_CREATE_LOAINHANVIEN_FAIL = "ERR_CREATE_LOAINHANVIEN_FAIL";
    public static final String ERR_UPDATE_LOAINHANVIEN_FAIL = "ERR_UPDATE_LOAINHANVIEN_FAIL";
    public static final String ERR_DELETE_LOAINHANVIEN_FAIL = "ERR_DELETE_LOAINHANVIEN_FAIL";
    public static final String ERR_LOAINHANVIEN_LOADED_FAIL = "ERR_LOAINHANVIEN_LOADED_FAIL";
    public static final String ERR_LOAINHANVIEN_LIST_NOT_FOUND = "ERR_LOAINHANVIEN_LIST_NOT_FOUND";
    public static final String ERR_LOAINHANVIEN_LIST_LOADED_FAIL = "ERR_LOAINHANVIEN_LIST_LOADED_FAIL";

    /** TAIKHOAN **/
    public static final String ERR_TAIKHOAN_NOT_FOUND = "ERR_TAIKHOAN_NOT_FOUND";
    public static final String ERR_CREATE_TAIKHOAN_FAIL = "ERR_CREATE_TAIKHOAN_FAIL";
    public static final String ERR_UPDATE_TAIKHOAN_FAIL = "ERR_UPDATE_TAIKHOAN_FAIL";
    public static final String ERR_DELETE_TAIKHOAN_FAIL = "ERR_DELETE_TAIKHOAN_FAIL";
    public static final String ERR_TAIKHOAN_LOADED_FAIL = "ERR_TAIKHOAN_LOADED_FAIL";
    public static final String ERR_TAIKHOAN_LIST_NOT_FOUND = "ERR_TAIKHOAN_LIST_NOT_FOUND";
    public static final String ERR_TAIKHOAN_LIST_LOADED_FAIL = "ERR_TAIKHOAN_LIST_LOADED_FAIL";

    /** LOAIHINHSUKIEN **/
    public static final String ERR_LOAIHINHSUKIEN_NOT_FOUND = "ERR_LOAIHINHSUKIEN_NOT_FOUND";
    public static final String ERR_CREATE_LOAIHINHSUKIEN_FAIL = "ERR_CREATE_LOAIHINHSUKIEN_FAIL";
    public static final String ERR_UPDATE_LOAIHINHSUKIEN_FAIL = "ERR_UPDATE_LOAIHINHSUKIEN_FAIL";
    public static final String ERR_DELETE_LOAIHINHSUKIEN_FAIL = "ERR_DELETE_LOAIHINHSUKIEN_FAIL";
    public static final String ERR_LOAIHINHSUKIEN_LOADED_FAIL = "ERR_LOAIHINHSUKIEN_LOADED_FAIL";
    public static final String ERR_LOAIHINHSUKIEN_LIST_NOT_FOUND = "ERR_LOAIHINHSUKIEN_LIST_NOT_FOUND";
    public static final String ERR_LOAIHINHSUKIEN_LIST_LOADED_FAIL = "ERR_LOAIHINHSUKIEN_LIST_LOADED_FAIL";

    /** PHIEUHEN **/
    public static final String ERR_PHIEUHEN_NOT_FOUND = "ERR_PHIEUHEN_NOT_FOUND";
    public static final String ERR_CREATE_PHIEUHEN_FAIL = "ERR_CREATE_PHIEUHEN_FAIL";
    public static final String ERR_UPDATE_PHIEUHEN_FAIL = "ERR_UPDATE_PHIEUHEN_FAIL";
    public static final String ERR_DELETE_PHIEUHEN_FAIL = "ERR_DELETE_PHIEUHEN_FAIL";
    public static final String ERR_PHIEUHEN_LOADED_FAIL = "ERR_PHIEUHEN_LOADED_FAIL";
    public static final String ERR_PHIEUHEN_LIST_NOT_FOUND = "ERR_PHIEUHEN_LIST_NOT_FOUND";
    public static final String ERR_PHIEUHEN_LIST_LOADED_FAIL = "ERR_PHIEUHEN_LIST_LOADED_FAIL";

    /** CHITIETHOADON **/
    public static final String ERR_CHITIETHOADON_NOT_FOUND = "ERR_CHITIETHOADON_NOT_FOUND";
    public static final String ERR_CREATE_CHITIETHOADON_FAIL = "ERR_CREATE_CHITIETHOADON_FAIL";
    public static final String ERR_UPDATE_CHITIETHOADON_FAIL = "ERR_UPDATE_CHITIETHOADON_FAIL";
    public static final String ERR_DELETE_CHITIETHOADON_FAIL = "ERR_DELETE_CHITIETHOADON_FAIL";
    public static final String ERR_CHITIETHOADON_LOADED_FAIL = "ERR_CHITIETHOADON_LOADED_FAIL";
    public static final String ERR_CHITIETHOADON_LIST_NOT_FOUND = "ERR_CHITIETHOADON_LIST_NOT_FOUND";
    public static final String ERR_CHITIETHOADON_LIST_LOADED_FAIL = "ERR_CHITIETHOADON_LIST_LOADED_FAIL";




}
