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
@Table(name = "hoadon",
        indexes = {
                @Index(name = "hoadon_idx", columnList = "maHoaDon,ngayLapHoaDon")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maHoaDon;

    @Column(name = "ngayLapHoaDon")
    private LocalDateTime ngayLapHoaDon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maNhaHang")
    private NhaHang nhaHangHD;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maNhanVien")
    private NhanVien nhanVienHD;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maKhachHang")
    private KhachHang khachHangHD;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hoaDonCTHD")
    private List<ChiTietHoaDon> chiTietHoaDons = new ArrayList<>();


}
