package com.Alkemy.JavaMod21.security.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    // access: /admin only authenticated CON usuarios es memoria


    @Bean
    SecurityFilterChain web(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/authbasic/publico/**").permitAll()
                        .requestMatchers("/authbasic/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                );
    http
        .formLogin(withDefaults()); // (1)
    /* .formLogin(form -> form
            .loginPage("/login")  // Página de login personalizada
            .defaultSuccessUrl("/dashboard", true)  // Redirección al éxito
            .failureUrl("/login?error=true")  // Redirección al fallo
            .permitAll()*/
    http
        .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll()
        );
    http
        .exceptionHandling(exception -> exception
                .accessDeniedPage("/access-denied")
        );

    http
        .httpBasic(withDefaults()); // (1)
    return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .roles("ADMIN")
                .build();

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}
