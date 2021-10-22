package com.hballaba.chucknorris.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("Hereeeee");
        http.authorizeRequests()
                .antMatchers("/jokes/**")
                .authenticated()
                .and()
                .formLogin()
                .and()
                .logout().logoutSuccessUrl("/");

        System.out.println("Hereeeee");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }
}
