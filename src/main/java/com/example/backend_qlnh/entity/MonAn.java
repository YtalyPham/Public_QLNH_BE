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
@Table(name = "monan",
        indexes = {
                @Index(name = "monan_idx", columnList = "maMonAn,tenMonAn")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonAn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maMonAn;

    @Column(name = "tenMonAn")
    @Type(type="org.hibernate.type.StringNVarcharType")
    private String tenMonAn;

    @Column(name = "loaiMonAn")
    @Type(type="org.hibernate.type.StringNVarcharType")
    private String loaiMonAn;

    @Column(name = "donGia")
    private Double donGia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maHinhAnh")
    private HinhAnh hinhAnhMA;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maNguyenLieu")
    private NguyenLieu nguyenLieuMA;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "monAnTD")
    private List<ThucDon> thucDons = new ArrayList<>();

}
