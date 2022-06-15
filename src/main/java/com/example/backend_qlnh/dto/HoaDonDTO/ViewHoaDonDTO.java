package com.example.backend_qlnh.dto.HoaDonDTO;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewHoaDonDTO {
    private Long maHoaDon;
    private LocalDateTime ngayLapHoaDon;
    private Long maNhaHang;
    private Long maNhanVien;
    private Long maKhachHang;
}
