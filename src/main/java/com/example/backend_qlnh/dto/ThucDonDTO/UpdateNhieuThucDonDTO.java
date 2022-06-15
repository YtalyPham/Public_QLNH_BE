package com.example.backend_qlnh.dto.ThucDonDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateNhieuThucDonDTO {
    private String tenThucDon;
    private Double donGia;
    private List<Long> listid;
}
