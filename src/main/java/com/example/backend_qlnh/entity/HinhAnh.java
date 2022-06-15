package com.example.backend_qlnh.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hinhanh")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HinhAnh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maHinhAnh;

    @Column(name = "maKey")
    private String maKey;

    @Column(name = "maUrl")
    private String maUrl;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hinhAnhKM")
    private List<KhuyenMai> khuyenMais = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hinhAnhDV")
    private List<DichVu> dichVus = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hinhAnhNH")
    private List<NhaHang> nhaHangs = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hinhAnhTD")
    private List<ThucDon> thucDons = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hinhAnhST")
    private List<SanhTiec> sanhTiecs = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hinhAnhTS")
    private List<TaiSan> taiSans = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hinhAnhMA")
    private List<MonAn> monAns = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hinhAnhKH")
    private List<KhachHang> khachHangs = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hinhAnhNV")
    private List<NhanVien> nhanViens = new ArrayList<>();









}
