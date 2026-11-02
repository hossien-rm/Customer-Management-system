package com.cms.config.security;

import com.cms.constant.ApplicationConstant;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;


public class JWTTokenGeneratorFilter extends OncePerRequestFilter {


    public static String generateToken(Authentication authentication,Environment env){

        String secret=env.getProperty(ApplicationConstant.JWT_SECRET_KEY,ApplicationConstant.JWT_SECRET_DEFAULT_VALUE);

        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder().setIssuer("CMS").setSubject("JWT Token")
                 .claim("username",authentication.getName())
                 .claim("authorities",authentication.getAuthorities().stream()
                         .map(GrantedAuthority::getAuthority)
                         .collect(Collectors.joining(",")))
                 .setIssuedAt(new Date())
                 .setExpiration(new Date(new Date().getTime()+ 30000000))
                 .signWith(secretKey).compact();
    }

    @Override
    protected void doFilterInternal( @NonNull  HttpServletRequest request
            , @NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {

        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();

        if( null != authentication && authentication.isAuthenticated() && !request.getRequestURI().equals("/api/login")) {

            Environment env=getEnvironment();

            String jwt = generateToken(authentication, env);

            response.setHeader(ApplicationConstant.JWT_HEADER,jwt);
        }
        filterChain.doFilter(request,response);
    }

}
