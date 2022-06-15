package com.example.backend_qlnh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "khachhang",
        indexes = {
                @Index(name = "khachhang_idx", columnList = "maKhachHang,tenKhachHang")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maKhachHang;

    @Column(name = "tenKhachHang")
    @Type(type="org.hibernate.type.StringNVarcharType")
    private String tenKhachHang;

    @Column(name = "email")
    private String email;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "gioiTinh")
    private boolean gioiTinh;

    @Column(name = "ngaySinh")
    private LocalDateTime ngaySinh;

    @Column(name = "diemTichLuy")
    private int diemTichLuy;

    @Column(name = "vangLai")
    private boolean vangLai;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maTaiKhoan")
    private User taiKhoanKH;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maHinhAnh")
    private HinhAnh hinhAnhKH;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "khachHangPDT")
    private List<PhieuDatTiec> phieuDatTiecs = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "khachHangHD")
    private List<HoaDon> hoaDons = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "khachHangDG")
    private List<DanhGia> danhGias = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "khachHangPH")
    private List<PhieuHen> phieuHens = new ArrayList<>();
    
}
