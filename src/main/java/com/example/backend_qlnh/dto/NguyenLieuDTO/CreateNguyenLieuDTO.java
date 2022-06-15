package com.example.backend_qlnh.dto.NguyenLieuDTO;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateNguyenLieuDTO {
    private Long maNguyenLieu;
    private String tenNguyenLieu;
    private LocalDateTime ngayNhap;
    private LocalDateTime ngayHetHan;
    private Double kg;
    private Double donGiaMua;
    private String ghiChu;
}
