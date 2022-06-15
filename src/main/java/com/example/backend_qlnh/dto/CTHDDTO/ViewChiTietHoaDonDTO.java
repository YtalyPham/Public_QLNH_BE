package com.example.backend_qlnh.dto.CTHDDTO;

import com.example.backend_qlnh.entity.HoaDon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewChiTietHoaDonDTO {
    private Long maCthd;
    private Double VAT;
    private Double tongTien;
    private Double thanhTien;
    private Double tienKhachDua;
    private Double tienThoi;
    private Double tienCoc;
    private Double tienPhatSinh;
    private Boolean thanhToan;
    private LocalDateTime ngayLapHoaDon;
    private Long maHoaDon;
    private Long maPhieuDatTiec;
    private Long maKhuyenMai;
}
