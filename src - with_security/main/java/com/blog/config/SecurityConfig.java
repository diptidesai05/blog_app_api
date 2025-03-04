package com.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.blog.security.JwtAuthenticationEntryPoint;
import com.blog.security.JwtAuthenticationFilter;

/*
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    	System.out.println("in SecurityConfig in securityFilterChain");
       http.csrf(csrf -> csrf.disable());
               // .authorizeRequests().
               // requestMatchers("/test").authenticated().requestMatchers("/api/v1/auth/login").permitAll()
              //  .anyRequest()
              //  .authenticated()
              // .and().exceptionHandling(ex -> ex.authenticationEntryPoint(point))
               // .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class); 
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        System.out.println("in SecurityConfig in authenticationManager");
    	return builder.getAuthenticationManager();
    }
    
    @Bean
	public BCryptPasswordEncoder passwordEncoder() {
    	System.out.println("in SecurityConfig in passwordEncoder");
		return new BCryptPasswordEncoder();
	}
}
*/