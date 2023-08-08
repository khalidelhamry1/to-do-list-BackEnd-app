package com.example.demo.service;

import com.example.demo.controller.AuthenticationResponse;
import com.example.demo.entities.Member;
import com.example.demo.entities.Role;
import com.example.demo.jwt.JwtService;
import com.example.demo.repository.MemberRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final MemberRepository memberRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(MemberRepository memberRepository, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.memberRepository = memberRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterReqeust reqeust) {
        var user=Member.builder()
                .firstName(reqeust.firstName())
                .lastName(reqeust.lastName())
                .email(reqeust.email())
                .password(reqeust.password())
                .role(Role.MEMBER)
                .build();

     memberRepository.save(user);
     var jwt=jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwt).build();
    }

    public AuthenticationResponse authenticate(Member reqeust) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        reqeust.getEmail(),
                        reqeust.getPassword()
                )
        );
        var user=memberRepository.findByEmail(reqeust.getEmail())
                .orElseThrow();
        var jwt=jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwt).build();
    }
}
