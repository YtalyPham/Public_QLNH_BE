package com.example.backend_qlnh.dto.LoaiNhanVienDTO;

import lombok.*;

import javax.persistence.Column;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateLoaiNhanVienDTO {
    private Long maLoaiNhanVien;
    private String tenLoai;
}
