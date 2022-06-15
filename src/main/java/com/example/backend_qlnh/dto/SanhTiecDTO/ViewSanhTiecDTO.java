package com.example.backend_qlnh.dto.SanhTiecDTO;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewSanhTiecDTO {
    private Long maSanhTiec;
    private String tenSanhTiec;
    private String viTri;
    private String kichThuoc;
    private String dienTich;
    private Double donGia;
    private String quayTrienLam;
    private String kieuBanTron;
    private String kieuBanDai;
    private String kieuLopHoc;
    private String kieuRapHat;
    private Long maHinhAnh;
}
