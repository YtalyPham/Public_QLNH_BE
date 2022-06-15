package com.example.backend_qlnh.controllers;

import com.example.backend_qlnh.entity.Role;
import com.example.backend_qlnh.exception.ResourceNotFoundException;
import com.example.backend_qlnh.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class VaiTroController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/vaitro/{id}")
    public ResponseEntity<Role> xemVaiTro(@PathVariable(value = "id") Long maid) throws ResourceNotFoundException {

        Role role = roleRepository.findById(maid)
                .orElseThrow(() -> new ResourceNotFoundException("Vai Trò này không tồn tại: " + maid));

        return ResponseEntity.ok().body(role);





    }

}
