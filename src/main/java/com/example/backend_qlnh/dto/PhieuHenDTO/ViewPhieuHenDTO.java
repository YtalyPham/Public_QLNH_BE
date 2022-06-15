package com.example.backend_qlnh.dto.PhieuHenDTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewPhieuHenDTO {
    private Long maPhieuHen;
    private int soLuongBan;
    private LocalDateTime ngayDatTiec;
    private LocalDateTime lichHen;
    private String ghiChu;
    private String buoi;
    private Long maLoaiHinhSuKien;
    private Long maKhachHang;
}
