package com.enigma.loan_app.controller;

import com.enigma.loan_app.constant.Message;
import com.enigma.loan_app.constant.PathApi;
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
@RequestMapping(PathApi.LOAN_TYPE)
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
                .message(Message.LOAN_TYPE_CREATED)
                .data(createdLoanType)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(PathApi.ID)
    public ResponseEntity<?> getLoanTypeById(
            @PathVariable String id
    ) {
        LoanType loanType = loanTypeService.findById(id);
        CommonResponse<LoanType> response = CommonResponse.<LoanType>builder()
                .message(Message.LOAN_TYPE_FOUND)
                .data(loanType)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getAllLoanTypes() {
        List<LoanType> loanTypes = loanTypeService.findAll();
        CommonResponse<List<LoanType>> response = CommonResponse.<List<LoanType>>builder()
                .message(Message.LOAN_TYPE_FOUNDS(loanTypes.size()))
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
                .message(Message.LOAN_TYPE_UPDATED)
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
                .message(Message.LOAN_TYPE_DELETED)
                .build();
        return ResponseEntity.ok(response);
    }

}
