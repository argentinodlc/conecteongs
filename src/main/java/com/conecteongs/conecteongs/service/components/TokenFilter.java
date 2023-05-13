package com.conecteongs.conecteongs.service.components;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.conecteongs.conecteongs.service.impl.TokenService;
import com.conecteongs.conecteongs.service.impl.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TokenFilter extends OncePerRequestFilter {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = retrieveToken(request);
		
		if (tokenService.isTokenValid(token)) {
			authenticate(token);
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	private String retrieveToken(HttpServletRequest request) {
		var authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader == null || authorizationHeader.isEmpty() || !authorizationHeader.startsWith("Bearer ")) {
            return null;
        }
        return authorizationHeader.substring(7);
	}
	
	private void authenticate(String token) {
		var subject = this.tokenService.getSubject(token);
		var user = this.userService.loadUserByUsername(subject);
		var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
}
