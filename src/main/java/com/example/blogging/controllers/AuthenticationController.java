package com.example.blogging.controllers;

import com.example.blogging.dto.JWTAuthenticationResponse;
import com.example.blogging.dto.SignInRequest;
import com.example.blogging.dto.SignUpRequest;
import com.example.blogging.entity.User;
import com.example.blogging.service.AuthenticationService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthenticationController {
    private  final AuthenticationService authenticationService;

    @PostMapping("/api/v1/auth/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest){
        return  ResponseEntity.ok(authenticationService.signup(signUpRequest));
    }
    @PostMapping("/api/v1/auth/signin")
    public ResponseEntity<JWTAuthenticationResponse>  signin(@RequestBody SignInRequest signInRequest){
        return  ResponseEntity.ok(authenticationService.signin(signInRequest));
    }
}
