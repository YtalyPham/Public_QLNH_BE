package com.example.backend_qlnh.dto.PhieuDatTiecDTO;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLHDTO {
    private String maPhieuDatTiec;
    private LocalDateTime lichHen;
}
