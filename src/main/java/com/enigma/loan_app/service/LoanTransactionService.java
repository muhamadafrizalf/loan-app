package com.enigma.loan_app.service;

import com.enigma.loan_app.dto.request.LoanTransactionRequest;
import com.enigma.loan_app.dto.response.LoanTransactionResponse;

public interface LoanTransactionService {
    LoanTransactionResponse create(LoanTransactionRequest loanTransactionRequest);
}
