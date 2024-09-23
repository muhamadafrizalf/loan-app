package com.enigma.loan_app.service.impl;

import com.enigma.loan_app.constant.EApprovalStatus;
import com.enigma.loan_app.constant.ELoanStatus;
import com.enigma.loan_app.constant.Message;
import com.enigma.loan_app.dto.request.ApproveLoanRequest;
import com.enigma.loan_app.dto.request.LoanTransactionRequest;
import com.enigma.loan_app.dto.response.LoanTransactionResponse;
import com.enigma.loan_app.entity.*;
import com.enigma.loan_app.repository.LoanTransactionRepository;
import com.enigma.loan_app.service.*;
import com.enigma.loan_app.util.ValidationUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanTransactionServiceImpl implements LoanTransactionService {
    private final LoanTransactionRepository loanTransactionRepository;
    private final LoanTypeService loanTypeService;
    private final InstallmentTypeService installmentTypeService;
    private final CustomerService customerService;
    private final UserService userService;
    private final ValidationUtil validationUtil;

    private LoanTransaction getLoanTransactionOrThrow(String id) {
        if (id == null || id.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.LOAN_TRANSACTION_ID_IS_EMPTY);
        return loanTransactionRepository.findById(id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND, Message.LOAN_TRANSACTION_NOT_FOUND));
    }

    public void validateMaximumLoan(Double nominal, Double maxLoan) {
        if (nominal > maxLoan) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.NOMINAL_IS_GREATER_THAN_MAX_LOAN);
    }

    private static LoanTransactionResponse mapToResponse(LoanTransaction loanTransaction) {
        return LoanTransactionResponse.builder()
                .id(loanTransaction.getId())
                .loanType(loanTransaction.getLoanType().getType())
                .maxLoan(loanTransaction.getLoanType().getMaxLoan().toString())
                .installmentType(loanTransaction.getInstalmentType().getInstallmentType().name())
                .customerFirstName(loanTransaction.getCustomer().getFirstName())
                .customerLastName(loanTransaction.getCustomer().getLastName())
                .customerEmail(loanTransaction.getCustomer().getUser().getEmail())
                .customerPhone(loanTransaction.getCustomer().getPhone())
                .nominal(loanTransaction.getNominal().toString())
                .approvedBy(loanTransaction.getApprovedBy())
                .approvalStatus(loanTransaction.getApprovalStatus() == null ? null : loanTransaction.getApprovalStatus().name())
                .loanTransactionDetails(loanTransaction.getLoanTransactionDetails() == null ? new ArrayList<>() : loanTransaction.getLoanTransactionDetails())
                .createdAt(loanTransaction.getCreatedAt())
                .updatedAt(loanTransaction.getUpdatedAt())
                .build();
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public LoanTransactionResponse create(LoanTransactionRequest loanTransactionRequest) {
        validationUtil.validate(loanTransactionRequest);

        LoanTransaction loanTransaction = new LoanTransaction();

        LoanType loanType = loanTypeService.findById(loanTransactionRequest.getLoanTypeId());
        loanTransaction.setLoanType(loanType);
        validateMaximumLoan(loanTransactionRequest.getNominal(), loanType.getMaxLoan());

        InstallmentType installmentType = installmentTypeService.findById(loanTransactionRequest.getInstallmentTypeId());
        loanTransaction.setInstalmentType(installmentType);

        Customer customer = customerService.findById(loanTransactionRequest.getCustomerId());
        loanTransaction.setCustomer(customer);

        loanTransaction.setNominal(loanTransactionRequest.getNominal());
        loanTransaction.setCreatedAt(LocalDateTime.now());

        loanTransactionRepository.saveAndFlush(loanTransaction);

        return mapToResponse(loanTransaction);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    @Override
    public LoanTransactionResponse findById(String id) {
        LoanTransaction loanTransaction = getLoanTransactionOrThrow(id);
        return mapToResponse(loanTransaction);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public LoanTransactionResponse approve(String adminId, ApproveLoanRequest approveLoanRequest) {
        validationUtil.validate(approveLoanRequest);

        AppUser admin = userService.loadUserById(adminId);
        LoanTransaction uprovedLoanTransaction = getLoanTransactionOrThrow(approveLoanRequest.getLoanId());
        if (uprovedLoanTransaction.getApprovalStatus() != null && uprovedLoanTransaction.getApprovalStatus().equals(EApprovalStatus.APPROVED))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.LOAN_TRANSACTION_ALREADY_APPROVED);

        uprovedLoanTransaction.setApprovedBy(admin.getEmail());
        uprovedLoanTransaction.setApprovedAt(LocalDateTime.now());
        uprovedLoanTransaction.setApprovalStatus(EApprovalStatus.APPROVED);
        uprovedLoanTransaction.setUpdatedAt(LocalDateTime.now());

        Double nominal = uprovedLoanTransaction.getNominal();
        Double interest = (approveLoanRequest.getInterestRate()+100)/100;
        int period = uprovedLoanTransaction.getInstalmentType().getInstallmentType().getValue();

        List<LoanTransactionDetail> loanTransactionDetails = new ArrayList<>();
        for (int i = 0; i < period; i++) {
            LoanTransactionDetail loanTransactionDetail = LoanTransactionDetail.builder()
                    .dueDate(LocalDate.now().plusMonths(i+1))
                    .nominal(nominal*interest/period)
                    .loanTransaction(uprovedLoanTransaction)
                    .loanStatus(ELoanStatus.UNPAID)
                    .createdAt(LocalDateTime.now())
                    .build();
            loanTransactionDetails.add(loanTransactionDetail);
        }

        uprovedLoanTransaction.setLoanTransactionDetails(loanTransactionDetails);
        loanTransactionRepository.saveAndFlush(uprovedLoanTransaction);

        return mapToResponse(uprovedLoanTransaction);
    }
}
