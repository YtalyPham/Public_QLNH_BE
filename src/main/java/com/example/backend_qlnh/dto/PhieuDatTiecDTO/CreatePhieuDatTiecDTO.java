package com.example.backend_qlnh.dto.PhieuDatTiecDTO;


import com.example.backend_qlnh.entity.LoaiHinhSuKien;
import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePhieuDatTiecDTO {
    private Long maPhieuDatTiec;
    private int soLuongBan;
    private LocalDateTime ngayToChuc;
    private LocalDateTime lichHen;
    private LocalDateTime ngayDatTiec;
    private String ghiChu;
    private String buoi;
    private boolean thanhToan;
    private Double thanhTien;
    private Double tienCoc;
    private Long maThucDon;
    private Long maTapDichVu;
    private Long maSanhTiec;
    private Long maLoaiHinhSuKien;
    private Long maKhachHang;
}
