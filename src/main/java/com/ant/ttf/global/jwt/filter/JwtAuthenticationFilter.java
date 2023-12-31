package com.ant.ttf.global.jwt.filter;


import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.ant.ttf.global.jwt.service.JwtTokenProvider;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Log
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	String token = jwtTokenProvider.resolveToken((HttpServletRequest) request); 
    	String requestURI = ((HttpServletRequest) request).getRequestURI();

         if (StringUtils.hasText(token) && jwtTokenProvider.validateToken(token)) {
             Authentication authentication = jwtTokenProvider.getAuthentication(token);
             SecurityContextHolder.getContext().setAuthentication(authentication);
             logger.info("Security context에 인증 정보를 저장했습니다, uri: {}", requestURI);
         } else {
             logger.debug("유효한 Jwt 토큰이 없습니다, uri: {}", requestURI);
         }

         chain.doFilter(request, response);
    }
}