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
@Table(name = "nhanvien",
        indexes = {
                @Index(name = "nhanvien_idx", columnList = "maNhanVien,tenNhanVien")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maNhanVien;

    @Column(name = "tenNhanVien")
    @Type(type="org.hibernate.type.StringNVarcharType")
    private String tenNhanVien;

    @Column(name = "email")
    private String email;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "cmnd")
    private String cmnd;

    @Column(name = "diaChi")
    @Type(type="org.hibernate.type.StringNVarcharType")
    private String diaChi;

    @Column(name = "gioiTinh")
    private boolean gioiTinh;

    @Column(name = "ngaySinh")
    private LocalDateTime ngaySinh;

    @Column(name = "ngayThue")
    private LocalDateTime ngayThue;

    @Column(name = "ngayLam")
    private int ngayLam;

    @Column(name = "ngayNghi")
    private int ngayNghi;

    @Column(name = "luongCoBan")
    private Double luongCoBan;

    @ManyToOne
    @JoinColumn(name = "maHinhAnh")
    private HinhAnh hinhAnhNV;

    @ManyToOne
    @JoinColumn(name = "maLoaiNhanVien")
    private LoaiNhanVien loaiNhanVienNV;

    @ManyToOne
    @JoinColumn(name = "maTaiKhoan")
    private User taiKhoanNV;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "nhanVienHD")
    private List<HoaDon> hoaDons = new ArrayList<>();






}
