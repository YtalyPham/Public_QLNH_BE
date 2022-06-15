package com.example.backend_qlnh.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "phieuhen",
        indexes = {
                @Index(name = "phieuhen_idx", columnList = "maPhieuHen")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhieuHen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maPhieuHen;

    @Column(name = "soLuongBan")
    private int soLuongBan;

    @Column(name = "ngayDatTiec")
    private LocalDateTime ngayDatTiec;

    @Column(name = "lichHen")
    private LocalDateTime lichHen;

    @Column(name = "ghiChu")
    private String ghiChu;

    @Column(name = "buoi")
    private String buoi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maLoaiHinhSuKien")
    private LoaiHinhSuKien loaiHinhSuKienPH;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maKhachHang")
    private KhachHang khachHangPH;






}
