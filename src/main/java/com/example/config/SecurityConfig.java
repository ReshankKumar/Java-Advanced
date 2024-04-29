package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//method method level security like preAuthorize("hasRole('admin')")
@EnableMethodSecurity
public class SecurityConfig {
@Bean
public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}
//In Memory authentication with dummy user
@Bean
public UserDetailsService userDetailsService(){
    UserDetails normal= User.builder()
            .username("reshank")
            .password(passwordEncoder().encode("Jo1so@"))
            .roles("USER")
            .build();
    UserDetails normal1= User.builder()
     .username("golu")
            .password(passwordEncoder().encode("Jo1so@#&"))
            .roles("ADMIN")
            .build();

    return new InMemoryUserDetailsManager(normal,normal1);
}
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
         httpSecurity
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/api/practice/**")
                            .permitAll().anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());
               return httpSecurity.build();

    }
}
