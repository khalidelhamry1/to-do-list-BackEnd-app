package com.example.demo.jwt;

import com.example.demo.service.MemberService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthentificationFilter extends OncePerRequestFilter {

   private final JwtService jwtService;
   private final UserDetailsService userDetailsService;

    public JwtAuthentificationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain
                                    ) throws ServletException, IOException {
        final String AuthHeader=request.getHeader("Authorization");
        final String jwt;
        final String memberEmail;
        //check jwt
        if (AuthHeader==null||!AuthHeader.startsWith("Bearer ")) {
          filterChain.doFilter(request,response);
          return;
        }
        jwt=AuthHeader.substring(7);
        //extract user email
        memberEmail=jwtService.extractEmail(jwt);
        if (memberEmail!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
            UserDetails userDetails= this.userDetailsService.loadUserByUsername(memberEmail);
            if (jwtService.isTokenValid(jwt,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                        new UsernamePasswordAuthenticationToken(
                                userDetails,null
                                ,userDetails.getAuthorities()

                        );
                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }
        }
        filterChain.doFilter(request,response);
    }

}
