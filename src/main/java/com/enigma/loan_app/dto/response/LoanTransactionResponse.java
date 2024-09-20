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
    private String loanTypeId;
    private String installmentTypeId;
    private String customerId;
    private Double nominal;
    private LocalDateTime approvedAt;
    private String approvedBy;
    private EApprovalStatus approvalStatus;
    private List<LoanTransactionDetail> loanTransactionDetails;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
