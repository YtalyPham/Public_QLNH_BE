package com.example.backend_qlnh.dto.KhuyenMaiDTO;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateKhuyenMaiDTO {
    private Long maKhuyenMai;
    private String tenKhuyenMai;
    private Double giamGia;
    private LocalDateTime thoiGianBatDau;
    private LocalDateTime thoiGianKetThuc;
    private String noiDung;
    private Long maHinhAnh;



}
