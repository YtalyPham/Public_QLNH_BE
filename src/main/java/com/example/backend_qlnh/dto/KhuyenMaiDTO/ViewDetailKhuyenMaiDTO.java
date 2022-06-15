package com.example.backend_qlnh.dto.KhuyenMaiDTO;

import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewDetailKhuyenMaiDTO {
    private Long maKhuyenMai;
    private String tenKhuyenMai;
    private Double giamGia;
    private LocalDateTime thoiGianBatDau;
    private LocalDateTime thoiGianKetThuc;
    private String noiDung;
    private Long maHinhAnh;
}
