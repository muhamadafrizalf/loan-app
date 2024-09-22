package com.enigma.loan_app.dto.request;

import com.enigma.loan_app.constant.Message;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
public class AuthRequest {
    @NotBlank(message = Message.EMAIL_IS_REQUIRED)
    @Email(message = Message.EMAIL_INVALID)
    private String email;
    @NotBlank(message = Message.PASSWORD_IS_REQUIRED)
    private String password;
    private String confirmPassword;
}
