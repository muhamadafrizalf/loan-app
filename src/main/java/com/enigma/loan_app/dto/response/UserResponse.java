package com.enigma.loan_app.dto.response;

import com.enigma.loan_app.entity.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UserResponse {
    private String email;
    private List<String> roles;
}
