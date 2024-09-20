package com.enigma.loan_app.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class InstallmentTypeRequest {
    private String installmentType;
}
