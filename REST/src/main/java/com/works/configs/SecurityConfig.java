package com.works.configs;

import com.works.services.CustomerDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final PasswordEncoder passwordEncoder;
    final CustomerDetailService detailService;

    // -> SQL Role
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailService).passwordEncoder(passwordEncoder);
    }

    // Login Type
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
        .httpBasic()
        .and()
        .authorizeRequests()
        .antMatchers("/product/**").hasRole("product")
        .antMatchers("/note/**").hasRole("note")
        .and()
        .csrf().disable().formLogin().disable();

        // H2 Console Permission
        http.headers().frameOptions().disable();

    }

}

/*
    	ali@mail.com -> product
    	veli@mail.com -> note
    	zehra@mail.com -> product, note
 */
