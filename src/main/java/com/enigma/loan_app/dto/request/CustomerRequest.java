package com.enigma.loan_app.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class CustomerRequest {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phone;
}
