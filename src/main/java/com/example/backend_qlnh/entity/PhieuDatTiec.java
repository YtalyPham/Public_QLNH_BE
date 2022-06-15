package com.example.backend_qlnh.entity;

import com.example.backend_qlnh.entity.generator.StringPrefixedSequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "phieudattiec",
        indexes = {
                @Index(name = "phieudattiec_idx", columnList = "maPhieuDatTiec,ngayToChuc")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhieuDatTiec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maPhieuDatTiec;

    @Column(name = "soLuongBan")
    private int soLuongBan;

    @Column(name = "ngayToChuc")
    private LocalDateTime ngayToChuc;

    @Column(name = "lichHen")
    private LocalDateTime lichHen;

    @Column(name = "ngayDatTiec")
    private LocalDateTime ngayDatTiec;

    @Column(name = "ghiChu")
    private String ghiChu;

    @Column(name = "buoi")
    private String buoi;

    @Column(name = "thanhToan")
    private boolean thanhToan;

    @Column(name = "thanhTien")
    private Double thanhTien;

    @Column(name = "tienCoc")
    private Double tienCoc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maThucDon")
    private ThucDon thucDonPDT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maTapDichVu")
    private TapDichVu tapDichVuPTD;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maSanhTiec")
    private SanhTiec sanhTiecPDT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maKhachHang")
    private KhachHang khachHangPDT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maLoaiHinhSuKien")
    private LoaiHinhSuKien loaiHinhSuKienPDT;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "phieuDatTiecCTHD")
    private List<ChiTietHoaDon> chiTietHoaDons = new ArrayList<>();





}
