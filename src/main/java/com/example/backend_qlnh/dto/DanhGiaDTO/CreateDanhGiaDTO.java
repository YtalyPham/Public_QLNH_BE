package com.example.backend_qlnh.dto.DanhGiaDTO;

import com.example.backend_qlnh.entity.KhachHang;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateDanhGiaDTO {
    private Long maDanhGia;
    private String  noiDung;
    private Integer soSao;
    private Long maKhachHang;
}
