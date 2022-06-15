package com.example.backend_qlnh.dto.MonAnDTO;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewDetailMonAnDTO {
    private Long maMonAn;
    private String tenMonAn;
    private String loaiMonAn;
    private Double donGia;
    private Long maHinhAnh;
    private Long maNguyenLieu;
}
