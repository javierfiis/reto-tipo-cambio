package com.jcallo.reto.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.jcallo.reto.dto.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

@RestController
public class UserController {

	@PostMapping("user")
	public User login(@RequestBody User user) {
		System.out.println("DEBUG: UserController.login called");
    String token = getJWTToken(user.getUsername());
		user.setToken(token);		
		return user;
		
	}

	private String getJWTToken(String username) {
		System.out.println("DEBUG: getJWTToken called");
		// Generate a secure 512-bit key for HS512
		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(key).compact();

		return "Bearer " + token;
	}
}