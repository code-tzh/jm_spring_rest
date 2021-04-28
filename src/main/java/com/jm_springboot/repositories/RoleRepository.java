package com.jm_springboot.repositories;

import com.jm_springboot.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository <Role, Long> {
    Role findByRole(String role);
}