package com.blog.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetailsService  userDetailsService;
	
	@Autowired
	private JwtTokenHelper jwtTokeHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//1. Get token
		String requestToken=request.getHeader("Authorization");
		System.out.println("requestToken "+requestToken);
		
		String username=null;
		String token=null;
		
		if(requestToken!=null && requestToken.startsWith("Bearer")){
			
			token=requestToken.substring(7);
			try {
			 username=jwtTokeHelper.getUsernameFromToken(token);
			}catch(IllegalArgumentException ie) {
				System.out.println("unable to get Jwt token");
				ie.printStackTrace();
			}catch(ExpiredJwtException ee) {
				System.out.println("jwt token expired");
				ee.printStackTrace();
			}catch(MalformedJwtException me) {
				System.out.println(" invalid jwt");
				me.printStackTrace();
			}
		}else {
			
			System.out.println("Request token does not starts with bearer");
		}
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			UserDetails userdetails=this.userDetailsService.loadUserByUsername(username);
			if(this.jwtTokeHelper.validateToken(token, userdetails))
			{
				UsernamePasswordAuthenticationToken userAuthToken=new UsernamePasswordAuthenticationToken(userdetails, null, userdetails.getAuthorities());
				userAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(userAuthToken);
				
			}else {
				System.out.println("invalid jwt token");
			}
			
			
		}else {
			System.out.println("username is null or context is not null");
		}
		filterChain.doFilter(request, response);
	}

}
