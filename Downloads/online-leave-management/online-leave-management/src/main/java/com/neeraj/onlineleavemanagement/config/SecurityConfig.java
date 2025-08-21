package com.neeraj.onlineleavemanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        return new InMemoryUserDetailsManager(
            User.withUsername("employee")
                .password(passwordEncoder().encode("emp123"))
                .roles("EMPLOYEE")
                .build(),

            User.withUsername("manager")
                .password(passwordEncoder().encode("mgr123"))
                .roles("MANAGER")
                .build()
        );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()  // static resources
                .requestMatchers("/login").permitAll()                           // login page allowed
                .requestMatchers("/leave/apply", "/apply-leave", "/leave/submit", "/my-leaves").hasRole("EMPLOYEE")
                .requestMatchers("/leave/pending", "/leave/approve/**", "/leave/reject/**").hasRole("MANAGER")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")       // custom login page URL
                .defaultSuccessUrl("/", true) // redirect after successful login (adjust '/' as needed)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            )
            .exceptionHandling(exception -> exception
                .accessDeniedPage("/access-denied") // handle 403 forbidden with a custom page
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
