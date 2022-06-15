package com.example.backend_qlnh.dto.NhanVienDTO;

import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateNhanVienDTO {
    private Long maNhanVien;
    private String tenNhanVien;
    private String email;
    private String sdt;
    private String cmnd;
    private String diaChi;
    private boolean gioiTinh;
    private LocalDateTime ngaySinh;
    private LocalDateTime ngayThue;
    private int ngayLam;
    private int ngayNghi;
    private Double luongCoBan;
    private Long maHinhAnh;
    private Long maLoaiNhanVien;
    private Long maTaiKhoan;



}
