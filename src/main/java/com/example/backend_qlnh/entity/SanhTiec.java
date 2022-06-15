package com.example.backend_qlnh.entity;

import com.example.backend_qlnh.entity.generator.StringPrefixedSequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sanhtiec",
        indexes = {
                @Index(name = "sanhtiec_idx", columnList = "maSanhTiec,tenSanhTiec")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SanhTiec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maSanhTiec;

    @Column(name = "tenSanhTiec")
    @Type(type="org.hibernate.type.StringNVarcharType")
    private String tenSanhTiec;

    @Column(name = "viTri")
    @Type(type="org.hibernate.type.StringNVarcharType")
    private String viTri;

    @Column(name = "kichThuoc")
    private String kichThuoc;

    @Column(name = "dienTich")
    private String dienTich;

    @Column(name = "donGia")
    private Double donGia;

    @Column(name = "quayTrienLam")
    @Type(type="org.hibernate.type.StringNVarcharType")
    private String quayTrienLam;

    @Column(name = "kieuBanTron")
    @Type(type="org.hibernate.type.StringNVarcharType")
    private String kieuBanTron;

    @Column(name = "kieuBanDai")
    @Type(type="org.hibernate.type.StringNVarcharType")
    private String kieuBanDai;

    @Column(name = "kieuLopHoc")
    @Type(type="org.hibernate.type.StringNVarcharType")
    private String kieuLopHoc;

    @Column(name = "kieuRapHat")
    @Type(type="org.hibernate.type.StringNVarcharType")
    private String kieuRapHat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maHinhAnh")
    private HinhAnh hinhAnhST;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sanhTiecPDT")
    private List<PhieuDatTiec> phieuDatTiecs = new ArrayList<>();
}
