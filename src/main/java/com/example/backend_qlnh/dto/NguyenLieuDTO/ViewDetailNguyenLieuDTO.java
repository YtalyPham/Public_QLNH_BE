package com.example.backend_qlnh.dto.NguyenLieuDTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewDetailNguyenLieuDTO {
    private Long maNguyenLieu;
    private String tenNguyenLieu;
    private LocalDateTime ngayNhap;
    private LocalDateTime ngayHetHan;
    private Double kg;
    private Double donGiaMua;
    private String ghiChu;
}
