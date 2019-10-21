package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationProvider securityProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/admit/**").hasRole("ADMIT")
                .antMatchers("/view/**").hasRole("USER")
                .antMatchers("/register/**").permitAll()
                .antMatchers("/doRegister/**").permitAll()
                .and()
                .formLogin().defaultSuccessUrl("/view",true).failureForwardUrl("/login")
                .and()
                .logout().logoutSuccessUrl("/")
                .and()
                .csrf().disable();
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return this.userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())   //在Spring Security 5.0中新增了多种加密方式，页改变了密码的格式
//                .withUser("qwe").password(new BCryptPasswordEncoder().encode("123456")).roles("USER")
//                .and()
//                .withUser("asd").password(new BCryptPasswordEncoder().encode("123456")).roles("ADMIT")
//                .and()
//                .withUser("zxc").password(new BCryptPasswordEncoder().encode("123456")).roles("USER", "ADMIT");
        auth.authenticationProvider(securityProvider);
    }
}
