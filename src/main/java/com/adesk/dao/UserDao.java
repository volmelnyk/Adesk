package com.adesk.dao;

import com.adesk.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.lang.annotation.Retention;

public interface UserDao extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    User findById(Long id);
    User findByEmail(String email);

}
