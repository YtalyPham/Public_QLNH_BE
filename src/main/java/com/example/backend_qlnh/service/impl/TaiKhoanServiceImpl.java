package com.example.backend_qlnh.service.impl;

import com.example.backend_qlnh.entity.User;
import com.example.backend_qlnh.repository.UserRepository;
import com.example.backend_qlnh.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaiKhoanServiceImpl implements TaiKhoanService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
