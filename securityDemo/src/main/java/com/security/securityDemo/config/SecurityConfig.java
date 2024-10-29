package com.security.securityDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
//                        )); // new session every time
//       return http.build();


        return http
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request
                        .anyRequest().authenticated())
                //.formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )).build();
    }

    // after  this we cant use default service any more
    @Bean
    public UserDetailsService userDetailsService(){
        // we can not return UserDetailsService because it was a interface
        // so to implement it we use InMemoryUserDetailsManager it was implement
        // UserDetailsManager and UserDetailsManager actual implement the UserDetailsService

        UserDetails user1 = User
                .withDefaultPasswordEncoder()
                .username("tiger")
                .password("scott")
                .roles("USER")
                .build();

        UserDetails user2 = User
                .withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("USER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }
}
