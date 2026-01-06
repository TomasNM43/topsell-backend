package com.topsell.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Desactivar CSRF (Cross-Site Request Forgery)
                // Esto es vital para que funcionen las APIs REST sin tokens de sesión de navegador.
                .csrf(csrf -> csrf.disable())

                // 2. Configurar quién puede entrar a dónde
                .authorizeHttpRequests(auth -> auth
                        // Permitimos entrar a CUALQUIER ruta que empiece con /api/ sin loguearse
                        .requestMatchers("/api/**").permitAll()
                        // Cualquier otra ruta (ej: /admin interno) sí pedirá login
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}