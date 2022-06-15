package com.example.backend_qlnh.entity;

import com.example.backend_qlnh.entity.generator.StringPrefixedSequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "nguyenlieu",
        indexes = {
                @Index(name = "nguyenlieu_idx", columnList = "maNguyenLieu,tenNguyenLieu")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NguyenLieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maNguyenLieu;

    @Column(name = "tenNguyenLieu")
    @Type(type="org.hibernate.type.StringNVarcharType")
    private String tenNguyenLieu;

    @Column(name = "ngayNhap")
    private LocalDateTime ngayNhap;

    @Column(name = "ngayHetHan")
    private LocalDateTime ngayHetHan;

    @Column(name = "kg")
    private Double kg;

    @Column(name = "donGiaMua")
    private Double donGiaMua;

    @Column(name = "ghiChu")
    @Type(type="org.hibernate.type.StringNVarcharType")
    private String ghiChu;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "nguyenLieuMA")
    private List<MonAn> monAns = new ArrayList<>();

}
