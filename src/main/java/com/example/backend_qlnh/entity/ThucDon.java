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
@Table(name = "thucdon",
        indexes = {
                @Index(name = "thucdon_idx", columnList = "maThucDon,tenThucDon")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThucDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "maThucDon")
    private Long maThucDon;

    @Column(name = "tenThucDon")
    @Type(type="org.hibernate.type.StringNVarcharType")
    private String tenThucDon;

    @Column(name = "setThucDon")
    private Integer setThucDon;

    @Column(name = "donGia")
    private Double donGia;

    @Column(name = "macDinh")
    private Boolean macDinh;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "thucDonPDT")
    private List<PhieuDatTiec> phieuDatTiecs = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maMonAn")
    private MonAn monAnTD;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maHinhAnh")
    private HinhAnh hinhAnhTD;



}
