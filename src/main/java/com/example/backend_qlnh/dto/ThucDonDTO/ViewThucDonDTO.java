package com.example.backend_qlnh.dto.ThucDonDTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewThucDonDTO {
    private Long maThucDon;
    private String tenThucDon;
    private Integer setThucDon;
    private Double donGia;
    private Boolean macDinh;
    private Long maMonAn;
    private Long maHinhAnh;

}
