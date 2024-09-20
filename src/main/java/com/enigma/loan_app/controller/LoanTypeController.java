package com.enigma.loan_app.controller;

import com.enigma.loan_app.dto.response.CommonResponse;
import com.enigma.loan_app.entity.LoanType;
import com.enigma.loan_app.service.LoanTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loan-types")
@PreAuthorize("hasAnyRole('ADMIN','STAFF')")
@RequiredArgsConstructor
public class LoanTypeController {
    private final LoanTypeService loanTypeService;

    @PostMapping
    public ResponseEntity<?> createLoanType(
            @RequestBody LoanType loanType
    ) {
        LoanType createdLoanType = loanTypeService.create(loanType);
        CommonResponse<LoanType> response = CommonResponse.<LoanType>builder()
                .message("Loan type created")
                .data(createdLoanType)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLoanTypeById(
            @PathVariable String id
    ) {
        LoanType loanType = loanTypeService.findById(id);
        CommonResponse<LoanType> response = CommonResponse.<LoanType>builder()
                .message("Loan type found")
                .data(loanType)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllLoanTypes() {
        List<LoanType> loanTypes = loanTypeService.findAll();
        CommonResponse<List<LoanType>> response = CommonResponse.<List<LoanType>>builder()
                .message("Loan types found")
                .data(loanTypes)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<?> updateLoanType(
            @RequestBody LoanType loanType
    ) {
        LoanType updatedLoanType = loanTypeService.update(loanType);
        CommonResponse<LoanType> response = CommonResponse.<LoanType>builder()
                .message("Loan type updated")
                .data(updatedLoanType)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLoanType(
            @PathVariable String id
    ) {
        loanTypeService.delete(id);
        CommonResponse<?> response = CommonResponse.builder()
                .message("Loan type deleted")
                .build();
        return ResponseEntity.ok(response);
    }

}
