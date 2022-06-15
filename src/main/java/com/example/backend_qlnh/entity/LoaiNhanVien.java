package com.example.backend_qlnh.entity;

import com.example.backend_qlnh.enums.ELoaiNhanVien;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "loainhanvien",
        indexes = {
                @Index(name = "loainhanvien_idx", columnList = "maLoaiNhanVien,tenLoai")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoaiNhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maLoaiNhanVien;

    @Column(name = "tenLoai")
    @Type(type="org.hibernate.type.StringNVarcharType")
    private String tenLoai;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loaiNhanVienNV")
    private List<NhanVien> nhanViens = new ArrayList<>();
}
