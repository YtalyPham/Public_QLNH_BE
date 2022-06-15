package com.example.backend_qlnh.dto.NhanVienDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewNhanVienDTO {
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
