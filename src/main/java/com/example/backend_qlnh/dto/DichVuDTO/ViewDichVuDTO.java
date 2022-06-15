package com.example.backend_qlnh.dto.DichVuDTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewDichVuDTO {
    private Long maDichVu;
    private String tenDichVu;
    private Double donGia;
    private int soLuong;
    private String ghiChu;
    private Long maHinhAnh;
}
