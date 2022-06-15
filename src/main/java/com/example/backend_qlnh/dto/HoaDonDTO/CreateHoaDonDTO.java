package com.example.backend_qlnh.dto.HoaDonDTO;

import com.example.backend_qlnh.entity.KhachHang;
import com.example.backend_qlnh.entity.NhaHang;
import com.example.backend_qlnh.entity.NhanVien;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateHoaDonDTO {
    private Long maHoaDon;
    private LocalDateTime ngayLapHoaDon;
    private Long maNhaHang;
    private Long maNhanVien;
    private Long maKhachHang;

}
