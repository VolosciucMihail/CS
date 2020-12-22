package com.example.ssc9.config;

import com.example.ssc9.security.CsrfTokenLoggerFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);

        http.csrf(c -> {
            c.ignoringAntMatchers("/csrfdisabled/**");
        });

        http.addFilterAfter(new CsrfTokenLoggerFilter(),
                CsrfFilter.class
        );
    }
}
