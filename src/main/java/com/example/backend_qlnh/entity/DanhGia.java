package com.example.backend_qlnh.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "danhgia",
        indexes = {
                @Index(name = "danhgia_idx", columnList = "maDanhGia,soSao")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DanhGia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maDanhGia;

    @Column(name = "noiDung")
    @Type(type="org.hibernate.type.StringNVarcharType")
    private String  noiDung;

    @Column(name = "soSao")
    private Integer soSao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maKhachHang")
    private KhachHang khachHangDG;


}
