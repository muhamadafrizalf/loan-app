package com.enigma.loan_app.dto.response;

import com.enigma.loan_app.constant.EApprovalStatus;
import com.enigma.loan_app.entity.LoanTransactionDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class LoanTransactionResponse {
    private String id;
    private String loanType;
    private String maxLoan;
    private String installmentType;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;
    private String customerPhone;
    private String nominal;
    private LocalDateTime approvedAt;
    private String approvedBy;
    private String approvalStatus;
    private List<LoanTransactionDetail> loanTransactionDetails;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
