package com.example.backend_qlnh.dto.ThucDonDTO;


import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateThucDonDTO {
    private Long maThucDon;
    private String tenThucDon;
    private Integer setThucDon;
    private Double donGia;
    private Boolean macDinh;
    private Long maMonAn;
    private Long maHinhAnh;

    public CreateThucDonDTO(String tenThucDon, Integer setThucDon, Double donGia, Boolean macDinh, Long maMonAn, Long maHinhAnh) {
        this.tenThucDon = tenThucDon;
        this.setThucDon = setThucDon;
        this.donGia = donGia;
        this.macDinh = macDinh;
        this.maMonAn = maMonAn;
        this.maHinhAnh = maHinhAnh;
    }
}
