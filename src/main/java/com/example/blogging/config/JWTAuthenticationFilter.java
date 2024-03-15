package com.example.blogging.config;

import com.example.blogging.service.JWTService;
import com.example.blogging.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.RequestMatcherDelegatingAuthenticationManagerResolver;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final UserService userService;

    private final RequestMatcher requestMatcher = new AntPathRequestMatcher("/api/v1/auth/**");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
//        String servletPath = request.getServletPath();

        if(this.requestMatcher.matches(request)){
            filterChain.doFilter(request,response);
            return;
        }


        final  String authHeader = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(authHeader) && org.apache.commons.lang3.StringUtils.startsWith(authHeader, "Bearer ")) {
            final String jwt;
            final String userEmail;
            jwt = authHeader.substring(7);
            userEmail = jwtService.extractUserName(jwt);

            if (StringUtils.isNotEmpty(userEmail)&& SecurityContextHolder.getContext().getAuthentication()==null){
                UserDetails userDetails=userService.userDetailsService().loadUserByUsername(userEmail);

                if (jwtService.isTokenValid(jwt,userDetails)){
                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

                    UsernamePasswordAuthenticationToken token =  new UsernamePasswordAuthenticationToken(
                            userDetails, null,userDetails.getAuthorities()
                    );
                    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    securityContext.setAuthentication(token);
                    SecurityContextHolder.setContext(securityContext);
                }
            }
        }
   /*
    if(StringUtils.isEmpty(authHeader) || !org.apache.commons.lang3.StringUtils.startsWith(authHeader, "Bearer ")) {
            filterChain.doFilter(request,response);
            return;
        }
        */

        filterChain.doFilter(request,response);
    }
}
