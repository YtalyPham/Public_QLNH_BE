package com.example.backend_qlnh.dto.QueryDTO;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PDTtheoNgayBuoi {
    private LocalDateTime ngayToChuc;
    private String buoi;
}
