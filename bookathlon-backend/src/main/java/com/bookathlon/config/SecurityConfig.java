package com.bookathlon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
/**
 * Questa classe configura la sicurezza dell'applicazione Spring Boot.
 * Definisce le regole di autorizzazione HTTP, la gestione del login e del logout,
 * e i bean per la cifratura delle password e la gestione dell'autenticazione.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig{
	/**
     */
	@Bean 
	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
          .authorizeHttpRequests(auth -> auth
              .requestMatchers("/","/images/**", "/register", "/login", "/css/**", "/js/**").permitAll()
              .anyRequest().authenticated()
          )
          .formLogin(form -> form
              .loginPage("/login")
              .defaultSuccessUrl("/", true)
              .permitAll()
          )
          .logout(logout -> logout
        		    .logoutUrl("/logout") // definisce l’URL per il logout
        		    .logoutSuccessUrl("/") // reindirizza alla login con messaggio
        		    .permitAll()
        		);

        return http.build();
    }
/**
     * Questo metodo fornisce un'istanza di `BCryptPasswordEncoder` per la cifratura delle password.
     */
    @Bean
     /**
     * Questo metodo espone il Bean `AuthenticationManager`, necessario per l'autenticazione.
     */
    public PasswordEncoder passwordEncoder() {	//cifra le password nel DB
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
	
}