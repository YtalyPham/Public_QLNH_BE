package com.example.backend_qlnh.dto.KhachHangDTO;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateKhachHangVangLaiDTO {
    private Long maKhachHang;
    private String tenKhachHang;
    private String email;
    private String sdt;
    private int diemTichLuy;
    private boolean vangLai;


}
