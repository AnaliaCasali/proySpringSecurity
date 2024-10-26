package com.Alkemy.JavaMod21.security.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class WebSecurityConfig {
    // testLogin: user & Using generated security password
    // access: /admin only aauthenticated
    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/authbasic/publico/**").permitAll()
                        .requestMatchers("/authbasic/admin/**").authenticated()
                );
    http
        .formLogin(withDefaults()); // (1)
    http
        .httpBasic(withDefaults()); // (1)
    return http.build();
    }
}