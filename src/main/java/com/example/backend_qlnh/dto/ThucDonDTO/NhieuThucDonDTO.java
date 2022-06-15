package com.example.backend_qlnh.dto.ThucDonDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NhieuThucDonDTO {
    private Long maThucDon;
    private String tenThucDon;
    private Integer setThucDon;
    private Double donGia;
    private Boolean macDinh;
    private List<Long> maNhieuMonAn;
    private Long maHinhAnh;
}
