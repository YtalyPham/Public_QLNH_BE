package com.example.backend_qlnh.service.impl;

import com.example.backend_qlnh.entity.Role;
import com.example.backend_qlnh.repository.RoleRepository;
import com.example.backend_qlnh.service.VaiTroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaiTroServiceImpl  implements VaiTroService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
