package com.example.backend_qlnh.entity;

import com.example.backend_qlnh.enums.ERole;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private ERole name;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vaiTroTK")
//    private List<TaiKhoan> taiKhoans = new ArrayList<>();

    public Role(ERole name) {
        this.name = name;
    }
}
