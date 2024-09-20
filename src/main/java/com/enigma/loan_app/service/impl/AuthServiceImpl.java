package com.enigma.loan_app.service.impl;

import com.enigma.loan_app.dto.request.AuthRequest;
import com.enigma.loan_app.entity.User;
import com.enigma.loan_app.repository.UserRepository;
import com.enigma.loan_app.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    @Override
    public User signUpCustomer(AuthRequest authRequest) {
        return null;
    }

    @Override
    public User signUpAdmin(AuthRequest authRequest) {
        return null;
    }

    @Override
    public User signIn(AuthRequest authRequest) {
        return null;
    }
}
