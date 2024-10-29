package com.security.securityDemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // overriding the default security for spring
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    // MyUserDetailsService class will injected automatically
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

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
                        .requestMatchers("register", "login")
                        .permitAll()
                        .anyRequest().authenticated())
                //.formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )).build();
    }

    // after  this we cant use default service any more
    //@Bean
//    public UserDetailsService userDetailsService(){
//        // we can not return UserDetailsService because it was a interface
//        // so to implement it we use InMemoryUserDetailsManager it was implement
//        // UserDetailsManager and UserDetailsManager actual implement the UserDetailsService
//
//        UserDetails user1 = User
//                .withDefaultPasswordEncoder()
//                .username("tiger")
//                .password("scott")
//                .roles("USER")
//                .build();
//        UserDetails user2 = User
//                .withDefaultPasswordEncoder()
//                .username("admin")
//                .password("admin")
//                .roles("USER", "ADMIN")
//                .build();
//
//
//        return new InMemoryUserDetailsManager(user1, user2);
//    }

    //Dealing with DataBase
    @Bean
    public AuthenticationProvider authenticationProvider(){
        // AuthenticationProvider was an interface
        // to implement it we use DAOAuthenticationProvider
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
       return config.getAuthenticationManager();
    }
}
