package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // disable CSRF (important for POST requests & tests)
            .csrf(csrf -> csrf.disable())

            // allow all requests without authentication
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            )

            // disable default login page
            .formLogin(form -> form.disable())

            // disable http basic auth
            .httpBasic(basic -> basic.disable());

        return http.build();
    }
}
