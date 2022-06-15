package com.example.backend_qlnh.dto.KhachHangDTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ViewKhachHangVangLaiDTO {
    private Long maKhachHang;
    private String tenKhachHang;
    private String email;
    private String sdt;
    private int diemTichLuy;
    private boolean vangLai;
}
