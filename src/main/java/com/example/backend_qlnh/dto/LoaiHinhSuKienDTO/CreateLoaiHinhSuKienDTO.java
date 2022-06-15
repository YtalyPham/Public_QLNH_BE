package com.example.backend_qlnh.dto.LoaiHinhSuKienDTO;

import lombok.*;



@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateLoaiHinhSuKienDTO {
    private Long maLoaiHinhSuKien;
    private String tenLoaiHinhSuKien;
}
