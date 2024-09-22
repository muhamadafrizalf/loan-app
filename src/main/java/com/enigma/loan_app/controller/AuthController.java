package com.enigma.loan_app.controller;

import com.enigma.loan_app.constant.Message;
import com.enigma.loan_app.constant.PathApi;
import com.enigma.loan_app.dto.request.AuthRequest;
import com.enigma.loan_app.dto.response.CommonResponse;
import com.enigma.loan_app.dto.response.LoginResponse;
import com.enigma.loan_app.dto.response.RegisterResponse;
import com.enigma.loan_app.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PathApi.AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping(PathApi.REGISTER)
    public ResponseEntity<?> registerCustomer (
            @RequestBody AuthRequest request
    ) {
        RegisterResponse registerResponse = authService.signUpCustomer(request);
        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .message(Message.REGISTER_SUCCESS)
                .data(registerResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping(PathApi.REGISTER_ADMIN)
    public ResponseEntity<?> registerAdmin (
            @RequestBody AuthRequest request
    ) {
        RegisterResponse registerResponse = authService.signUpAdmin(request);
        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .message(Message.REGISTER_SUCCESS_ADMIN)
                .data(registerResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping(PathApi.LOGIN)
    public ResponseEntity<?> login (
            @RequestBody AuthRequest request
    ) {
        LoginResponse loginResponse = authService.signIn(request);
        CommonResponse<LoginResponse> response = CommonResponse.<LoginResponse>builder()
                .message(Message.LOGIN_SUCCESS)
                .data(loginResponse)
                .build();
        return ResponseEntity.ok(response);
    }

}
