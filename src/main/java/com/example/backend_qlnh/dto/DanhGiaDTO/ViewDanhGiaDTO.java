package com.example.backend_qlnh.dto.DanhGiaDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewDanhGiaDTO {
    private Long maDanhGia;
    private String  noiDung;
    private Integer soSao;
    private Long maKhachHang;
}
