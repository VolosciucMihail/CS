package com.example.ssc9.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    @Autowired
    private CustomCsrfTokenRepository customCsrfTokenRepository; //your custom csrfToken repository impl class

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf().csrfTokenRepository(customCsrfTokenRepository) //set your custom csrf impl in httpSecurity
                .and()
                .authorizeRequests()
                .antMatchers().permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling();
    }

}
