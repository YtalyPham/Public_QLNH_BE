package com.example.backend_qlnh.dto.KhachHangDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewKhachHangDTO {
    private Long maKhachHang;
    private String tenKhachHang;
    private String email;
    private String sdt;
    private boolean gioiTinh;
    private LocalDateTime ngaySinh;
    private int diemTichLuy;
    private boolean vangLai;
    private Long maTaiKhoan;
    private Long maHinhAnh;
}
