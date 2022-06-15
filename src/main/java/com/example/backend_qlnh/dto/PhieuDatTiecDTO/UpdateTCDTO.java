package com.example.backend_qlnh.dto.PhieuDatTiecDTO;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTCDTO {
    private String maPhieuDatTiec;
    private Double tienCoc;
}
