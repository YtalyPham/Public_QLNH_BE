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
@Table(name = "khuyenmai",
        indexes = {
                @Index(name = "khuyenmai_idx", columnList = "maKhuyenMai,tenKhuyenMai")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KhuyenMai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maKhuyenMai;

    @Column(name = "tenKhuyenMai")
    @Type(type="org.hibernate.type.StringNVarcharType")
    private String tenKhuyenMai;

    @Column(name = "giamGia")
    private Double giamGia;

    @Column(name = "thoiGianBatDau")
    private LocalDateTime thoiGianBatDau;

    @Column(name = "thoiGianKetThuc")
    private LocalDateTime thoiGianKetThuc;

    @Column(name = "noiDung")
    @Type(type="org.hibernate.type.StringNVarcharType")
    private String noiDung;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maHinhAnh")
    private HinhAnh hinhAnhKM;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "khuyenMaiCTHD")
    private List<ChiTietHoaDon> chiTietHoaDons = new ArrayList<>();

}
