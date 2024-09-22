package com.enigma.loan_app.dto.request;

import com.enigma.loan_app.constant.Message;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^\\+?[0-9 ]{10,15}$",
            message = Message.PHONE_INVALID)
    private String phone;
}
