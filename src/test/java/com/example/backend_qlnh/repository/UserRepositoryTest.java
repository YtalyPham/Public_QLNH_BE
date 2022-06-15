package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.constants.ErrorCode;
import com.example.backend_qlnh.entity.User;
import com.example.backend_qlnh.entity.Role;
import com.example.backend_qlnh.exception.DataNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserRepositoryTest {

//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    RoleRepository roleRepository;
//
//    @Test
//    public void testCreateTaiKhoan1() throws Exception{
//        Optional<Role> optvaiTro= roleRepository.findById(1l);
//        Role role;
//        if (optvaiTro.isPresent()){
//           role = optvaiTro.get();
//        } else throw new DataNotFoundException(ErrorCode.ERR_VAITRO_NOT_FOUND);
//
//        User user = new User();
//        user.setId(1L);
//        user.setMafirebase("111");
//        user.setUsername("0908123");
//        user.setPassword("123456");
//        user.setRoles(role);
//
//        User user1 = userRepository.save(user);
//
//        Assertions.assertNotNull(user1);
//        Assertions.assertEquals(1l, role.getId());
//
//    }
//    @Test
//    public void testCreateTaiKhoan2() throws Exception{
//        Optional<Role> optvaiTro= roleRepository.findById(2l);
//        Role role;
//        if (optvaiTro.isPresent()){
//            role = optvaiTro.get();
//        } else throw new DataNotFoundException(ErrorCode.ERR_VAITRO_NOT_FOUND);
//
//        User user = new User();
//        user.setId(2L);
//        user.setMaFirebase("111");
//        user.setUsername("0908123");
//        user.setPassword("123456");
//        user.setRole(role);
//
//        User user1 = userRepository.save(user);
//
//        Assertions.assertNotNull(user1);
//        Assertions.assertEquals(2l, role.getId());
//
//    }




}
