package com.enigma.loan_app.controller;

import com.enigma.loan_app.constant.Message;
import com.enigma.loan_app.constant.PathApi;
import com.enigma.loan_app.dto.request.LoanTransactionRequest;
import com.enigma.loan_app.dto.response.CommonResponse;
import com.enigma.loan_app.dto.response.LoanTransactionResponse;
import com.enigma.loan_app.service.LoanTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PathApi.LOAN_TRANSACTION)
@RequiredArgsConstructor
public class LoanTransactionController {
    private final LoanTransactionService loanTransactionService;

    @PreAuthorize("hasAnyRole('CUSTOMER')")
    @PostMapping
    public ResponseEntity<?> createTransaction(
            @RequestBody LoanTransactionRequest loanTransactionRequest
    ) {
        LoanTransactionResponse loanTransactionResponse = loanTransactionService.create(loanTransactionRequest);
        CommonResponse<LoanTransactionResponse> response = CommonResponse.<LoanTransactionResponse>builder()
                .message(Message.LOAN_TRANSACTION_CREATED)
                .data(loanTransactionResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(PathApi.ID)
    public ResponseEntity<?> getTransactionById(
            @PathVariable("id") String id
    ) {
        LoanTransactionResponse loanTransactionResponse = loanTransactionService.findById(id);
        CommonResponse<LoanTransactionResponse> response = CommonResponse.<LoanTransactionResponse>builder()
                .message(Message.LOAN_TRANSACTION_FOUND)
                .data(loanTransactionResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<?> approveTransaction() {
        return null;
    }

}
