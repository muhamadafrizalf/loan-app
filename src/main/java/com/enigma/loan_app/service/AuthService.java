package com.enigma.loan_app.service;

import com.enigma.loan_app.dto.request.AuthRequest;
import com.enigma.loan_app.entity.User;

public interface AuthService {
    User signUpCustomer(AuthRequest authRequest);
    User signUpAdmin(AuthRequest authRequest);
    User signIn(AuthRequest authRequest);
}
