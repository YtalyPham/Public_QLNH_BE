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

@Entity
@Table(name = "taisan",
        indexes = {
                @Index(name = "taisan_idx", columnList = "maTaiSan,tenTaiSan")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaiSan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "maTaiSan")
    private Long maTaiSan;

    @Column(name = "tenTaiSan")
    @Type(type="org.hibernate.type.StringNVarcharType")
    private String tenTaiSan;

    @Column(name = "soLuong")
    private Integer soLuong;

    @Column(name = "tinhTrang")
    private boolean tinhTrang;

    @Column(name = "ghiChu")
    @Type(type="org.hibernate.type.StringNVarcharType")
    private String ghiChu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maHinhAnh")
    private HinhAnh hinhAnhTS;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maNhaHang")
    private NhaHang nhahang;


}
