package com.example.demo.db;

import com.example.demo.entity.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MyUserDetails implements UserDetails {

    private User user;

    private Collection<? extends GrantedAuthority> authorities;

    public MyUserDetails(User user,Collection<? extends GrantedAuthority> authorities){
        super();
        this.user = user;
        this.authorities = authorities;
    }

    private static final long serialVersionUID = 1L;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.getState().equals(User.STATE_ACCOUNT_EXPIRED);
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.getState().equals(User.STATE_LOCK);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getState().equals(User.STATE_TOKEN_EXPIRED);
    }

    @Override
    public boolean isEnabled() {
        return user.getState().equals(User.STATE_NORMAL);
    }
}
