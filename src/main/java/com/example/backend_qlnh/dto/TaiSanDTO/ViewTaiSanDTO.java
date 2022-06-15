package com.example.backend_qlnh.dto.TaiSanDTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewTaiSanDTO {
    private Long maTaiSan;
    private String tenTaiSan;
    private Integer soLuong;
    private boolean tinhTrang;
    private String ghiChu;
    private Long maHinhAnh;
    private Long maNhahang;
}
