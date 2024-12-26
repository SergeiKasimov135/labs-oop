package ru.ssau.tk.kasimovserzhantov.labsoop.lab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.security.JwtAuthTokenFilter;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.security.LoginSuccessHandler;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.service.DefaultUserDetailsService;

@Configuration
public class SecurityBeans {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthTokenFilter authJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler() {
        return new LoginSuccessHandler();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(DefaultUserDetailsService userDetailsService) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
         return http
                 .csrf(AbstractHttpConfigurer::disable) // Отключение CSRF-защиты
                 .authorizeHttpRequests(authorize -> authorize
                         .requestMatchers("/login", "/register", "/css/**", "/registere").permitAll() // Разрешить доступ без аутентификации
                         .anyRequest().authenticated() // Все остальные запросы требуют аутентификации
                 )
                 .formLogin(formLogin -> formLogin
                         .loginPage("/login") // Указание кастомной страницы логина
                         .successHandler(loginSuccessHandler()) // Обработчик успешной аутентификации
                         .permitAll() // Разрешить доступ к странице логина
                 )
                 .logout(logout -> logout
                         .logoutUrl("/logout") // URL выхода из системы
                         .logoutSuccessUrl("/login?logout") // Перенаправление после выхода
                         .permitAll() // Разрешить доступ к функции logout
                 )
                 .sessionManagement(session -> session
                         .maximumSessions(1) // Ограничить до одной активной сессии на пользователя
                         .maxSessionsPreventsLogin(false) // При повторном входе завершить предыдущую сессию
                 )
                 .build();
    }

}
