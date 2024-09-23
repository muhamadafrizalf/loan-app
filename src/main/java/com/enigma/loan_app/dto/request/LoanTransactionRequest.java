package com.enigma.loan_app.dto.request;

import com.enigma.loan_app.constant.Message;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class LoanTransactionRequest {
    @NotBlank(message = Message.LOAN_TYPE_ID_IS_EMPTY)
    private String loanTypeId;
    @NotBlank(message = Message.INSTALLMENT_TYPE_ID_IS_EMPTY)
    private String installmentTypeId;
    @NotBlank(message = Message.CUSTOMER_ID_IS_EMPTY)
    private String customerId;
    @NotNull(message = Message.NOMINAL_IS_REQUIRED)
    @Positive(message = Message.NOMINAL_MUST_POSITIVE)
    private Double nominal;
}
