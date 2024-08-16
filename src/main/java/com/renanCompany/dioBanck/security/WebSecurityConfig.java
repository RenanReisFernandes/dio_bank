package com.renanCompany.dioBanck.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Desabilita CSRF para simplificação (configure conforme necessário)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/public/**").permitAll() // Permitir acesso público a URLs específicas
                .requestMatchers(HttpMethod.POST, "/api/**").hasRole("USER") // Restringir acesso POST para usuários autenticados
                .anyRequest().authenticated() // Todas as outras requisições precisam de autenticação
            )
            .httpBasic(); // Usa autenticação básica HTTP

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
