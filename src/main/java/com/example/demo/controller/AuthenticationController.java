package com.example.demo.controller;

import com.example.demo.entities.Member;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.RegisterReqeust;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register( @RequestBody RegisterReqeust reqeust) {
       return ResponseEntity.ok(authenticationService.register(reqeust));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate( @RequestBody Member reqeust) {
        return ResponseEntity.ok(authenticationService.authenticate(reqeust));
    }
}
