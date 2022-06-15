package com.example.backend_qlnh.dto.CTHDDTO;

import com.example.backend_qlnh.entity.HoaDon;

import lombok.*;

import javax.persistence.Column;
import java.time.LocalDateTime;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateChiTietHoaDonDTO {
    private Long maCthd;
    private Double VAT;
    private Double tongTien;
    private Double thanhTien;
    private Double tienKhachDua;
    private Double tienThoi;
    private Boolean thanhToan;
    private Double tienCoc;
    private Double tienPhatSinh;
    private LocalDateTime ngayLapHoaDon;
    private Long maHoaDon;
    private Long maPhieuDatTiec;
    private Long maKhuyenMai;

}
