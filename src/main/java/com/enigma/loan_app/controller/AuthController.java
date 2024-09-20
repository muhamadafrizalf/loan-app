package com.enigma.loan_app.controller;

import com.enigma.loan_app.dto.request.AuthRequest;
import com.enigma.loan_app.dto.response.CommonResponse;
import com.enigma.loan_app.dto.response.LoginResponse;
import com.enigma.loan_app.dto.response.RegisterResponse;
import com.enigma.loan_app.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("Hello World");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerCustomer (@RequestBody AuthRequest request) {
        RegisterResponse registerResponse = authService.signUpCustomer(request);
        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .message("Register success")
                .data(registerResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup/admin")
    public ResponseEntity<?> registerAdmin (@RequestBody AuthRequest request) {
        RegisterResponse registerResponse = authService.signUpAdmin(request);
        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .message("Register success")
                .data(registerResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login (@RequestBody AuthRequest request) {
        LoginResponse loginResponse = authService.signIn(request);
        CommonResponse<LoginResponse> response = CommonResponse.<LoginResponse>builder()
                .message("Login success")
                .data(loginResponse)
                .build();
        return ResponseEntity.ok(response);
    }

}
