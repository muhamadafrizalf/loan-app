package com.enigma.loan_app.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class RegisterResponse {
    private String email;
    private List<String> roles;
}
