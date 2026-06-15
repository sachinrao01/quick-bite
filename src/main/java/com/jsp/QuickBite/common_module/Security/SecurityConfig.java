package com.jsp.QuickBite.common_module.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf-> csrf.disable())
                .formLogin(form->form.disable())
                .authorizeHttpRequests(auth->
                        auth.requestMatchers("/quickbite/user/**").permitAll()
                                .requestMatchers("/quickbite/**").permitAll()
                              /*  .requestMatchers("/quickbite/fooditem/**").permitAll()
                                .requestMatchers("/quickbite/orders/**").permitAll()
                                .requestMatchers("/quickbite/payments/**").permitAll()
                                .requestMatchers("/quickbite/restaurant/**").permitAll()
                                .requestMatchers("/quickbite/cart/**").permitAll()*/
                                .anyRequest().authenticated());


        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}