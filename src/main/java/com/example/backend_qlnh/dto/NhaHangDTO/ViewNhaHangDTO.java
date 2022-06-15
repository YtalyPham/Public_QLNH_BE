package com.example.backend_qlnh.dto.NhaHangDTO;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewNhaHangDTO {
    private Long maNhaHang;
    private String tenNhaHang;
    private String email;
    private String diaChi;
    private String sdt;
    private String dienTich;
    private Long maHinhAnh;
}
