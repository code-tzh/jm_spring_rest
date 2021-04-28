package com.jm_springboot.repositories;

import com.jm_springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
    @Query("from User u inner JOIN FETCH u.roles as roles where u.email = :email")
    User getUserByEmail(@Param("email") String email);
}