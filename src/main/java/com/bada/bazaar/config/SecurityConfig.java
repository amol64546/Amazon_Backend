package com.bada.bazaar.config;

import com.bada.bazaar.error.CustomAccessDeniedHandler;
import com.bada.bazaar.error.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final AuthenticationProvider authenticationProvider;
  private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
  private final CustomAccessDeniedHandler customAccessDeniedHandler;


  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
      .cors(AbstractHttpConfigurer::disable)
      .csrf(AbstractHttpConfigurer::disable)
      .authorizeHttpRequests(auth -> auth
//        .requestMatchers("/v1/auth/**", "/swagger/**").permitAll()
//        .anyRequest().authenticated()
        .anyRequest().permitAll()
      )
      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .authenticationProvider(authenticationProvider)
      .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//      .exceptionHandling(exception -> exception
//        .authenticationEntryPoint(customAuthenticationEntryPoint)
//        .accessDeniedHandler(customAccessDeniedHandler)
//      )
      .build();


  }



}