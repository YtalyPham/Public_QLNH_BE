package com.example.backend_qlnh.repository;

import com.example.backend_qlnh.entity.Role;
import com.example.backend_qlnh.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
}
