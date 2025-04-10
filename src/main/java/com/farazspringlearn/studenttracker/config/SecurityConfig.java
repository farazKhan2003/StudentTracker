package com.farazspringlearn.studenttracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;

/**
 * Configuration file that will inject a BCryptPasswordEncoder to the other files.
 * It also allows for HTTP requests, disables CSRF protection as there are no user session cookies.
 */
@Configuration
public class SecurityConfig {

  /**
     * Bean which will allow spring to inject the passwordEncoder.
     *
     * @return PasswordEncoder the BCryptPasswordEncoder which will hash the passwords
   */

  @Bean
    public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
     * This method allows handling of HTTP requests while removing CSRF protection.
     *
     * @param http takes in any HTTP code and authenticates it
     * @return authenticated HTTP code
     * @throws Exception Throws exception when unrecognised HTTP code is passed
   */

  @Bean
    public DefaultSecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
                .csrf(csrf -> csrf.disable()) // 👈 This is the modern way to disable CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/student/**").permitAll()
                        //wont block any student: post/getting/put/delete mapping
                        .requestMatchers("/admin/login").permitAll()
                        //wont block login mapping.
                        .anyRequest().authenticated()
                )
                .build();
  }

}
