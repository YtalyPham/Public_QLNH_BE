package com.example.backend_qlnh.dto.TapDichVuDTO;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NhieuTapDichVuDTO {
    private Long maTapDichVu;
    private String tenTapDichVu;
    private List<Long> maNhieuDichVu;

}
