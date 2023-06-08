package com.example.springjwttoken.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.parameters.P;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig  {

    private final JWTFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.authorizeHttpRequests(authorization -> {
//            authorization.requestMatchers("/admin").hasRole("ADMIN");
            authorization.requestMatchers("/hello","/showUserInfo").hasAnyRole("ADMIN","USER");
            authorization.anyRequest().permitAll();
        });
       httpSecurity.formLogin().loginPage("/auth/login").loginProcessingUrl("/process_login")
               .defaultSuccessUrl("/hello",true)
               .failureUrl("/auth/login?error").and().logout().logoutUrl("/logout").logoutSuccessUrl("/auth/login")
               .and()
               .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

       httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

}
