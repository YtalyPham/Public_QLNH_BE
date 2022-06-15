package com.example.backend_qlnh.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "loaihinhsukien",
        indexes = {
                @Index(name = "loaihinhsukien_idx", columnList = "maLoaiHinhSuKien")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoaiHinhSuKien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maLoaiHinhSuKien;

    @Column(name = "tenLoaiHinhSuKien")
    private String tenLoaiHinhSuKien;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loaiHinhSuKienPDT")
    private List<PhieuDatTiec> phieuDatTiecs = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loaiHinhSuKienPH")
    private List<PhieuHen> phieuHens = new ArrayList<>();




}
