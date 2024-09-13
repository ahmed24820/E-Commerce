package com.my_project.e_commerce.Security.CustomFilter;

import com.my_project.e_commerce.Exceptions.JWTExpiration;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class CustomAuthenticationFilter extends OncePerRequestFilter {

   private final JwtService jwtservice;
   private final  UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request
            ,@NonNull HttpServletResponse response
            ,@NonNull FilterChain filterChain) throws ServletException, IOException,JWTExpiration
    {
        final String requestHeader=request.getHeader("Authorization");
        final String jwt;
        final String username;
        if(requestHeader == null || !requestHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        jwt=requestHeader.substring(7);
        username=jwtservice.ExtractUsername(jwt);
        if (username != null|| SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails= this.userDetailsService.loadUserByUsername(username);
            if (jwtservice.valid(jwt,userDetails)){
                UsernamePasswordAuthenticationToken AuthToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
                AuthToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(AuthToken);
            }else {
                throw new JWTExpiration("jwt already expired use the refresh token to get new one");
            }
        }
        filterChain.doFilter(request,response);
    }
}
