package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User findByUsername(String name);
    int insertForUser(User user);
}
