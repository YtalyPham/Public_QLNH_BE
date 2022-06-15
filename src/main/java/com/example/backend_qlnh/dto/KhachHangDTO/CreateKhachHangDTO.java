package com.example.backend_qlnh.dto.KhachHangDTO;

import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateKhachHangDTO {
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
