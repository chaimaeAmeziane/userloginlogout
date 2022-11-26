package com.example.userloginlogout.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource datasource;

    @Bean //Include Autowired in the class customer...
    public UserDetailsService userDetailsService()
    {
        return new CustomerUserDetailsService();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authentificationProvider()
    {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(authentificationProvider());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        /*Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer name =((CustomerUserDetails)principal).getId();
      */
                 http.authorizeRequests().antMatchers("/userspace/{id}").authenticated().anyRequest().permitAll()
                .and().formLogin().usernameParameter("email").defaultSuccessUrl("/userspace").permitAll()
                .and().logout().logoutSuccessUrl("/").permitAll();
        //http.authorizeRequests().antMatchers("/user/{id}/**").access("@guard.checkUserId(authentication,#id)");
    }
}
