package tipolt.andre.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import tipolt.andre.spring.services.FilterToken;

@Configuration
public class WebSecurityConfig {

    @Autowired
    private FilterToken filterToken;

    // @Bean
    // SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    //     return http.csrf().disable().authorizeHttpRequests(
    //         (authorizeHttpRequest) -> {
    //             authorizeHttpRequest.requestMatchers(HttpMethod.POST, "/login").permitAll();
    //             authorizeHttpRequest.requestMatchers(HttpMethod.GET, "/login").permitAll();
    //             authorizeHttpRequest.anyRequest().permitAll();
    //         }
    //     )
    //     .build();
    // }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.httpBasic().and().csrf().disable().authorizeRequests()
        .requestMatchers(HttpMethod.POST, "/login")
        .permitAll()
        .requestMatchers(HttpMethod.GET, "/login")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and().addFilterBefore(filterToken, UsernamePasswordAuthenticationFilter.class)
        .build();

    }
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
