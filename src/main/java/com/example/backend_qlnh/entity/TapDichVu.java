package com.example.backend_qlnh.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tapdichvu")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TapDichVu {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @JoinColumn(name="maTapDichVu")
        private Long maTapDichVu;

        @JoinColumn(name="tenTapDichVu")
        private String tenTapDichVu;

        @OneToMany(fetch = FetchType.LAZY, mappedBy = "tapDichVuPTD")
        private List<PhieuDatTiec> phieuDatTiecs = new ArrayList<>();

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "maDichVu")
        private DichVu dichVuTDV;

        public TapDichVu(String tenTapDichVu, DichVu dichVuTDV) {
                this.tenTapDichVu = tenTapDichVu;
                this.dichVuTDV = dichVuTDV;
        }
}
