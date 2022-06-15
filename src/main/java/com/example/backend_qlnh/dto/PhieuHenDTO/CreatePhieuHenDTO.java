package com.example.backend_qlnh.dto.PhieuHenDTO;

import lombok.*;

import java.time.LocalDateTime;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePhieuHenDTO {
    private Long maPhieuHen;
    private int soLuongBan;
    private LocalDateTime ngayDatTiec;
    private LocalDateTime lichHen;
    private String ghiChu;
    private String buoi;
    private Long maLoaiHinhSuKien;
    private Long maKhachHang;
}
