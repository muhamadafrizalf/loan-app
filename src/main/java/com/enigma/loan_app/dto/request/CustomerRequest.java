package com.enigma.loan_app.dto.request;

import com.enigma.loan_app.constant.Message;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class CustomerRequest {
    private String id;
    @NotBlank(message = Message.NAME_IS_REQUIRED)
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    @NotBlank(message = Message.PHONE_IS_REQUIRED)
    private String phone;
}
