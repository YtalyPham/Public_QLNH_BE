package com.example.backend_qlnh.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;


    private String mafirebase;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMafirebase() {
        return mafirebase;
    }

    public void setMafirebase(String mafirebase) {
        this.mafirebase = mafirebase;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
