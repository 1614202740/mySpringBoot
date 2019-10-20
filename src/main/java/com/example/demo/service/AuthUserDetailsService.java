package com.example.demo.service;

import com.example.demo.db.MyUserDetails;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import com.example.demo.mapper.UserMapper;
import com.example.demo.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userDetailsService")
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        try{
            User user = userMapper.findByUsername(name);
            if (user!=null){
                List<UserRole> userRoles = userRoleMapper.findByUserId(user.getId());
                Collection<GrantedAuthority> authorities = new ArrayList<>();
                for (UserRole userRole:userRoles){
                    String roleName = userRole.getRole().getName();
                    SimpleGrantedAuthority grant = new SimpleGrantedAuthority(roleName);
                    authorities.add(grant);
                }
                userDetails = new MyUserDetails(user,authorities);
            } else {
                throw new UsernameNotFoundException("该用户名不存在！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return userDetails;
    }
}
