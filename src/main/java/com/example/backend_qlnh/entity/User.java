package com.example.backend_qlnh.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mafirebase")
    private String mafirebase;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "maVaiTro")
//    private Role roleTK;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "taiKhoanNV")
    private List<NhanVien> nhanViens = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "taiKhoanKH")
    private List<KhachHang> khachHangs = new ArrayList<>();

    public User(String username, String mafirebase, String password) {
        this.username = username;
        this.mafirebase = mafirebase;
        this.password = password;
    }

    public User(Long id, String mafirebase, String username, String password, Set<Role> roles, List<NhanVien> nhanViens, List<KhachHang> khachHangs) {
        this.id = id;
        this.mafirebase = mafirebase;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.nhanViens = nhanViens;
        this.khachHangs = khachHangs;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMafirebase() {
        return mafirebase;
    }

    public void setMafirebase(String mafirebase) {
        this.mafirebase = mafirebase;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<NhanVien> getNhanViens() {
        return nhanViens;
    }

    public void setNhanViens(List<NhanVien> nhanViens) {
        this.nhanViens = nhanViens;
    }

    public List<KhachHang> getKhachHangs() {
        return khachHangs;
    }

    public void setKhachHangs(List<KhachHang> khachHangs) {
        this.khachHangs = khachHangs;
    }
}
