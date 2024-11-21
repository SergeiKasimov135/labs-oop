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
                 .csrf(AbstractHttpConfigurer::disable)
                 .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/lab/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/lab/math-functions/list", "/lab/points/function/{functionId}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/lab/math-functions", "/lab/points").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/lab/math-functions/{functionId:\\d+}", "/lab/points/{pointId:\\d+}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/lab/math-functions}", "/lab/points").authenticated()
                        .anyRequest().authenticated())
                 .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                 .authenticationProvider(authenticationProvider(null))
                 .addFilterBefore(authJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                 .build();
    }

}
