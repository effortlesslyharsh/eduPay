package com.edupay.authentication.utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.edupay.authentication.data.model.EduPayUser;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EduPayJWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	    private AuthenticationManager authenticationManager;

	    public EduPayJWTAuthenticationFilter (AuthenticationManager authenticationManager) {
	        this.authenticationManager = authenticationManager;

	        setFilterProcessesUrl("/api/services/controller/user/login"); 
	    }

	    @Override
	    public Authentication attemptAuthentication(HttpServletRequest req,
	                                                HttpServletResponse res) throws AuthenticationException {
	        try {
	            EduPayUser creds = new ObjectMapper()
	                    .readValue(req.getInputStream(), EduPayUser.class);

	            return authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                            creds.getUserName(),
	                            creds.getPassword(),
	                            new ArrayList<>())
	            );
	        } catch (IOException e) {
	            throw new RuntimeException(e);
	        }
	    }

	    @Override
	    protected void successfulAuthentication(HttpServletRequest req,
	                                            HttpServletResponse res,
	                                            FilterChain chain,
	                                            Authentication auth) throws IOException {
	        String token = JWT.create()
	                .withSubject(((EduPayUser) auth.getPrincipal()).getUserName())
	                .withExpiresAt(new Date(System.currentTimeMillis() + EduPayAuthenticationConstants.EXPIRATION_TIME))
	                .sign(Algorithm.HMAC512(EduPayAuthenticationConstants.SECRET.getBytes()));

	        String body = ((EduPayUser) auth.getPrincipal()).getUserName() + " " + token;

	        res.getWriter().write(body);
	        res.getWriter().flush();
	    }
}
