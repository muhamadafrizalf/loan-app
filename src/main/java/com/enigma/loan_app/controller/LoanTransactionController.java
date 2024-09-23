package com.enigma.loan_app.controller;

import com.enigma.loan_app.constant.Message;
import com.enigma.loan_app.constant.PathApi;
import com.enigma.loan_app.dto.request.ApproveLoanRequest;
import com.enigma.loan_app.dto.request.LoanTransactionRequest;
import com.enigma.loan_app.dto.response.CommonResponse;
import com.enigma.loan_app.dto.response.LoanTransactionResponse;
import com.enigma.loan_app.security.JwtUtil;
import com.enigma.loan_app.service.LoanTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(PathApi.LOAN_TRANSACTION)
@RequiredArgsConstructor
public class LoanTransactionController {
    private final LoanTransactionService loanTransactionService;
    private final JwtUtil jwtUtil;

    @PreAuthorize("hasAnyRole('CUSTOMER')")
    @PostMapping
    public ResponseEntity<?> createTransaction(
            @RequestHeader("Authorization") String headerAuth,
            @RequestBody LoanTransactionRequest loanTransactionRequest
    ) {
        /// FOR DEVELOPMENT ///
//        Map<String,String> userInfo = jwtUtil.getUserInfoByHeader(headerAuth);
//        if (loanTransactionRequest.getCustomerId() == null) {loanTransactionRequest.setCustomerId(userInfo.get("userId"));}
        /// REMOVE ON RELEASE ///
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

    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    @PutMapping(PathApi.APPROVE)
    public ResponseEntity<?> approveTransaction(
            @PathVariable("id") String adminId,
            @RequestHeader("Authorization") String headerAuth,
            @RequestBody ApproveLoanRequest approveLoanRequest
            ) {
        /// FOR DEVELOPMENT ///
        Map<String,String> userInfo = jwtUtil.getUserInfoByHeader(headerAuth);
        String id = adminId;
        /// REMOVE ON RELEASE ///
        LoanTransactionResponse loanTransactionResponse = loanTransactionService.approve(id, approveLoanRequest);
        CommonResponse<LoanTransactionResponse> response = CommonResponse.<LoanTransactionResponse>builder()
                .message(Message.LOAN_TRANSACTION_APPROVED)
                .data(loanTransactionResponse)
                .build();
        return ResponseEntity.ok(response);
    }

}
