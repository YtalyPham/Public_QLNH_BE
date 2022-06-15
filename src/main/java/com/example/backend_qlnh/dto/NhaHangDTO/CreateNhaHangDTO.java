package com.example.backend_qlnh.dto.NhaHangDTO;

import com.example.backend_qlnh.entity.HinhAnh;
import com.example.backend_qlnh.entity.TaiSan;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateNhaHangDTO {
    private String tenNhaHang;
    private String email;
    private String diaChi;
    private String sdt;
    private String dienTich;
    private Long maHinhAnh;
}
