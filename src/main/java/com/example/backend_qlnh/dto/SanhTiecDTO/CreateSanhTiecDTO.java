package com.example.backend_qlnh.dto.SanhTiecDTO;

import com.example.backend_qlnh.entity.HinhAnh;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateSanhTiecDTO {
    private Long maSanhTiec;
    private String tenSanhTiec;
    private String viTri;
    private String kichThuoc;
    private String dienTich;
    private Double donGia;
    private String quayTrienLam;
    private String kieuBanTron;
    private String kieuBanDai;
    private String kieuLopHoc;
    private String kieuRapHat;
    private Long maHinhAnh;
}
