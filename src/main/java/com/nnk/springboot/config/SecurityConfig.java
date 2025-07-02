package com.nnk.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


/**
 * Gère la sécurité de l'application avec Spring Security.
 * Définit les accès, la page de login et le chiffrement des mots de passe.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configure la sécurité HTTP.
     * Autorise l'accès aux ressources (CSS, JS, images) et aux pages login/logout.
     * Autorise l'accès à /user/** seulement aux admins.
     * Demande une connexion pour les autres pages.
     * Configure les pages de connexion et de déconnexion.
     * Désactive CSRF.
     *
     * @param http objet de configuration de sécurité
     * @return la configuration de sécurité
     * @throws Exception en cas d'erreur
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/login", "/logout").permitAll()
                        .requestMatchers("/user/**").hasAuthority("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin((formLogin) -> formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/bidList/list")
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .deleteCookies("JSESSIONID")
                        .invalidateHttpSession(true)
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authBuilder.build();
    }

    /**
     * Utilise BCrypt pour sécuriser les mots de passe.
     *
     * @return un encodeur de mot de passe
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}