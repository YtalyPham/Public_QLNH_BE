package com.example.backend_qlnh.dto.TapDichVuDTO;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTapDichVuDTO {
    private Long maTapDichVu;
    private String tenTapDichVu;
    private Long maDichVu;

    public CreateTapDichVuDTO(String tenTapDichVu, Long maDichVu) {
        this.tenTapDichVu = tenTapDichVu;
        this.maDichVu = maDichVu;
    }
}
