package com.example.backend_qlnh.dto.LoaiNhanVienDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewDetailLoaiNhanVien {
    private Long maLoaiNhanVien;
    private String tenLoai;
}
