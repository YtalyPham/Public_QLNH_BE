package com.example.backend_qlnh.dto.TaiSanDTO;

import com.example.backend_qlnh.entity.HinhAnh;
import com.example.backend_qlnh.entity.NhaHang;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaiSanDTO {
    private Long maTaiSan;
    private String tenTaiSan;
    private Integer soLuong;
    private boolean tinhTrang;
    private String ghiChu;
    private Long maHinhAnh;
    private Long maNhaHang;
}
