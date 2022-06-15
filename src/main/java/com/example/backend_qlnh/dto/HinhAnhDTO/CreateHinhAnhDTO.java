package com.example.backend_qlnh.dto.HinhAnhDTO;

import lombok.*;

import javax.persistence.Column;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateHinhAnhDTO {
    private Long maHinhAnh;
    private String maKey;
    private String maUrl;
}
