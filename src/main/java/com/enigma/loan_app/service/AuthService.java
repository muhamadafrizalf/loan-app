package com.enigma.loan_app.service;

import com.enigma.loan_app.dto.request.AuthRequest;
import com.enigma.loan_app.dto.response.LoginResponse;
import com.enigma.loan_app.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse signUpCustomer(AuthRequest authRequest);
    RegisterResponse signUpAdmin(AuthRequest authRequest);
    LoginResponse signIn(AuthRequest authRequest);
}
