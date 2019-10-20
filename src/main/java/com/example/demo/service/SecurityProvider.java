package com.example.demo.service;

import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("securityProvider")
public class SecurityProvider implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    public SecurityProvider(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token
                = (UsernamePasswordAuthenticationToken) authentication;
        String username = token.getName();
        UserDetails userDetails = null;

        if (username!=null){
            userDetails = userDetailsService.loadUserByUsername(username);
        }
        System.out.println("$$"+userDetails);

        if (userDetails==null){
            throw new UsernameNotFoundException("用户名/密码无效！");
        } else if (userDetails.isEnabled()){
            System.out.println("该用户已被禁用");
            throw new DisabledException("用户已被禁用");
        } else if (userDetails.isAccountNonExpired()) {
            System.out.println("该用户账号已过期");
            throw new LockedException("账号已过期");
        }else if (userDetails.isAccountNonLocked()) {
            System.out.println("该用户账号已被锁定");
            throw new LockedException("账号已被锁定");
        }else if (userDetails.isCredentialsNonExpired()) {
            System.out.println("该用户凭证已过期");
            throw new LockedException("凭证已过期");
        }

        String password = userDetails.getPassword();

        if (!password.equals(token.getCredentials())){
            throw new BadCredentialsException("Invalid username/password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.equals(aClass);
    }
}
