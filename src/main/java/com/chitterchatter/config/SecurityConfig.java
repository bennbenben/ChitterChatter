package com.chitterchatter.config;

import java.util.Arrays;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.chitterchatter.constants.AppConstants;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfig {

  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http.securityContext().requireExplicitSave(false).and()
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
        .cors().configurationSource(new CorsConfigurationSource() {
          @Override
          public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.setAllowedOrigins(AppConstants.frontEndPort);
            corsConfiguration.setAllowedMethods(AppConstants.regexAsString);
            corsConfiguration.setAllowedHeaders(AppConstants.regexAsString);
            corsConfiguration.setExposedHeaders(Arrays.asList("Authorization"));
            corsConfiguration.setAllowCredentials(true);
            corsConfiguration.setMaxAge(3600L);
            return corsConfiguration;
          }
        })
        .and().authorizeHttpRequests()
          .requestMatchers("/helloworld").authenticated()
          .requestMatchers("/hellomyworld").permitAll()
        .and().formLogin()
        .and().httpBasic();

    return http.build();

  }

}
