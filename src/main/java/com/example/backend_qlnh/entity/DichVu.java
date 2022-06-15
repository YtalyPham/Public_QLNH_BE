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
@Table(name = "dichvu",
        indexes = {
                @Index(name = "dichvu_idx", columnList = "maDichVu,tenDichVu")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DichVu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name="maDichVu")
    private Long maDichVu;

    @Column(name = "tenDichVu")
    @Type(type="org.hibernate.type.StringNVarcharType")
    private String tenDichVu;

    @Column(name = "donGia")
    private Double donGia;

    @Column(name = "soLuong")
    private int soLuong;

    @Column(name = "ghiChu")
    @Type(type="org.hibernate.type.StringNVarcharType")
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name = "maHinhAnh")
    private HinhAnh hinhAnhDV;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dichVuTDV")
    private List<TapDichVu> tapDichVus = new ArrayList<>();



}
