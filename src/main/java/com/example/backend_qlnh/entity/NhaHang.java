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
@Table(name = "nhahang",
        indexes = {
                @Index(name = "nhahang_idx", columnList = "maNhaHang,tenNhaHang")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NhaHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maNhaHang;

    @Column(name = "tenNhaHang")
    @Type(type="org.hibernate.type.StringNVarcharType")
    private String tenNhaHang;

    @Column(name = "email")
    private String email;

    @Column(name = "diaChi")
    @Type(type="org.hibernate.type.StringNVarcharType")
    private String diaChi;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "dienTich")
    private String dienTich;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "nhahang")
    private List<TaiSan> taiSans = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maHinhAnh")
    private HinhAnh hinhAnhNH;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "nhaHangHD")
    private List<HoaDon> hoaDons = new ArrayList<>();


}
