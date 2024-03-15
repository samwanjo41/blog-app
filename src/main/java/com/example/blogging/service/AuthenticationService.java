package com.example.blogging.service;

import com.example.blogging.dto.JWTAuthenticationResponse;
import com.example.blogging.dto.SignInRequest;
import com.example.blogging.dto.SignUpRequest;
import com.example.blogging.entity.User;

public interface AuthenticationService {
    User signup(SignUpRequest signUpRequest);
    JWTAuthenticationResponse signin(SignInRequest signInRequest);
}
