package com.example.util;

import com.example.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Login, Integer> {
    Login findByUsername(String userName);
    Login save(Login datalogin);
    void deleteByUsername(String username);


}
