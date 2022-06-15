package com.example.backend_qlnh.entity;

import com.example.backend_qlnh.entity.generator.StringPrefixedSequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chitiethoadon",
        indexes = {
                @Index(name = "cthd_idx", columnList = "maCthd")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietHoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maCthd;

    @Column(name = "vat")
    private Double VAT;

    @Column(name = "tongTien")
    private Double tongTien;

    @Column(name = "thanhTien")
    private Double thanhTien;

    @Column(name = "tienKhachDua")
    private Double tienKhachDua;

    @Column(name = "tienThoi")
    private Double tienThoi;

    @Column(name = "tienCoc")
    private Double tienCoc;

    @Column(name = "tienPhatSinh")
    private Double tienPhatSinh;

    @Column(name = "thanhToan")
    private Boolean thanhToan;

    @Column(name = "ngayLapHoaDon")
    private LocalDateTime ngayLapHoaDon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maHoaDon")
    private HoaDon hoaDonCTHD;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maPhieuDatTiec")
    private PhieuDatTiec phieuDatTiecCTHD;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maKhuyenMai")
    private KhuyenMai khuyenMaiCTHD;


}
