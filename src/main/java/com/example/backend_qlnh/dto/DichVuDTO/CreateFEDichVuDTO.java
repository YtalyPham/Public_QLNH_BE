package com.example.backend_qlnh.dto.DichVuDTO;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateFEDichVuDTO {
    private Long maDichVu;
    private String tenDichVu;
    private String donGia;
    private String soLuong;
    private String ghiChu;
    private Long maHinhAnh;
}
