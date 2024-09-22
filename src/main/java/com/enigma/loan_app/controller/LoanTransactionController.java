package com.enigma.loan_app.controller;

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
@RequestMapping("/api/transactions")
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
                .message("Successfully created transaction")
                .data(loanTransactionResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTransactionById(
            @PathVariable("id") String id
    ) {
        LoanTransactionResponse loanTransactionResponse = loanTransactionService.findById(id);
        CommonResponse<LoanTransactionResponse> response = CommonResponse.<LoanTransactionResponse>builder()
                .message("Successfully retrieved transaction")
                .data(loanTransactionResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<?> approveTransaction() {
        return null;
    }

}
