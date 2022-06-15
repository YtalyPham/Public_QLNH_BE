package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.entity.Role;
import com.example.backend_qlnh.enums.ERole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoleRepositoryTest {
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void CreateVaiTro1() throws Exception{
        Role role = new Role();
        role.setId(1l);
        role.setName(ERole.ROLE_USER);
        Role role1 = roleRepository.save(role);
        Assertions.assertNotNull(role1);
    }
    @Test
    public void CreateVaiTro2() throws Exception{
        Role role = new Role();
        role.setId(2l);
        role.setName(ERole.ROLE_ADMIN);
        Role role1 = roleRepository.save(role);
        Assertions.assertNotNull(role1);
    }



}
