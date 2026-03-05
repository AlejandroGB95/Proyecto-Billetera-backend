package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desactivamos CSRF para que deje enviar datos desde el front
            .cors(cors -> cors.build())    // Activamos la configuración CORS que definiremos abajo
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // ⚠️ ¡OJO! Esto permite acceso total sin contraseña (ideal para pruebas ahora mismo)
            );
        return http.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addMapping(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("https://proyecto-app-billetera.vercel.app") // Tu URL de Vercel
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}