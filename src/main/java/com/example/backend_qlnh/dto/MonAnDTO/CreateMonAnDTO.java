package com.example.backend_qlnh.dto.MonAnDTO;

import com.example.backend_qlnh.entity.HinhAnh;
import com.example.backend_qlnh.entity.NguyenLieu;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMonAnDTO {
    private Long maMonAn;
    private String tenMonAn;
    private String loaiMonAn;
    private Double donGia;
    private Long maHinhAnh;
    private Long maNguyenLieu;

    public CreateMonAnDTO(String tenMonAn, String loaiMonAn, Double donGia, Long maHinhAnh, Long maNguyenLieu) {
        this.tenMonAn = tenMonAn;
        this.loaiMonAn = loaiMonAn;
        this.donGia = donGia;
        this.maHinhAnh = maHinhAnh;
        this.maNguyenLieu = maNguyenLieu;
    }
}
