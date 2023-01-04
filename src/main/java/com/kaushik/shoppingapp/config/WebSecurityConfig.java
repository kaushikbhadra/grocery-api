package com.kaushik.shoppingapp.config;

import com.kaushik.shoppingapp.security.JWTAuthenticationEntryPoint;
import com.kaushik.shoppingapp.security.JWTAuthenticationFilter;
import com.kaushik.shoppingapp.service.security.AppUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JWTAuthenticationFilter jwtAuthenticationFilter;

    private static final String [] URL_LIST = {
           "/product/**",
            "/products/**",
            "/auth/**"
    };
    private final AppUserDetailsService appUserDetailsService;
    @Bean
    public ModelMapper modelMapper() {
        return new  ModelMapper();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/user/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers(URL_LIST)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .authenticationProvider(authenticationProvider())
                .exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(appUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
