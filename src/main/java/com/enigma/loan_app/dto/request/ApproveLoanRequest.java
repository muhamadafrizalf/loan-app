package com.enigma.loan_app.dto.request;

import com.enigma.loan_app.constant.Message;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ApproveLoanRequest {
    @NotBlank(message = Message.LOAN_TRANSACTION_ID_IS_EMPTY)
    private String loanId;
    @Positive(message = Message.INTEREST_RATE_MUST_POSITIVE)
    @NotNull(message = Message.INTEREST_RATE_IS_REQUIRED)
    private Double interestRate;
}
