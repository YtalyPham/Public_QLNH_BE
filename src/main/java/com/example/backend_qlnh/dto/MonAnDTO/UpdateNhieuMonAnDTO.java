package com.example.backend_qlnh.dto.MonAnDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateNhieuMonAnDTO {
    private String tenMonAn;
    private String loaiMonAn;
    private Double donGia;
    private List<Long> listid;
}
