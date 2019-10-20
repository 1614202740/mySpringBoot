package com.example.demo.mapper;

import com.example.demo.entity.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleMapper {
    List<UserRole> findByUserId(Integer id);
}
