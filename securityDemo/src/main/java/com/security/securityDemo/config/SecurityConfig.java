package com.security.securityDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // overriding the default security for spring
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(customizer -> customizer.disable()); // disable csrf
//        http
//                .authorizeHttpRequests(request -> request
//                        .anyRequest().authenticated()); // authentication applied
//        http.formLogin(Customizer.withDefaults()); // enable form login for website
//        http.httpBasic(Customizer.withDefaults()); // we have to enable this for restAPI
//        http
//                .sessionManagement(session ->
//                        session.sessionCreationPolicy(
//                                SessionCreationPolicy.STATELESS
//                        )); // new session every time//       return http.build();


        return http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )).build();
    }
}
