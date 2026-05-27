package com.jcallo.reto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.jcallo.reto.security.JWTAuthorizationFilter;

@SpringBootApplication
public class JwtTipoDeCambioApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtTipoDeCambioApplication.class, args);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
					.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeHttpRequests(authz -> authz
					.requestMatchers(new MvcRequestMatcher(introspector, "/user")).permitAll()
					.requestMatchers(new MvcRequestMatcher(introspector, "/api/**")).authenticated()
					.anyRequest().authenticated()
				);
		return http.build();
		}
	}

