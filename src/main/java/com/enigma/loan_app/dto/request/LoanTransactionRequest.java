package com.enigma.loan_app.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class LoanTransactionRequest {
    private String loanTypeId;
    private String installmentTypeId;
    private String customerId;
    private Double nominal;
}
