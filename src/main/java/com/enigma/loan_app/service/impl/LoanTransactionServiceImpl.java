package com.enigma.loan_app.service.impl;

import com.enigma.loan_app.constant.Message;
import com.enigma.loan_app.dto.request.LoanTransactionRequest;
import com.enigma.loan_app.dto.response.LoanTransactionResponse;
import com.enigma.loan_app.entity.Customer;
import com.enigma.loan_app.entity.InstallmentType;
import com.enigma.loan_app.entity.LoanTransaction;
import com.enigma.loan_app.entity.LoanType;
import com.enigma.loan_app.repository.LoanTransactionRepository;
import com.enigma.loan_app.service.CustomerService;
import com.enigma.loan_app.service.InstallmentTypeService;
import com.enigma.loan_app.service.LoanTransactionService;
import com.enigma.loan_app.service.LoanTypeService;
import com.enigma.loan_app.util.ValidationUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class LoanTransactionServiceImpl implements LoanTransactionService {
    private final LoanTransactionRepository loanTransactionRepository;
    private final LoanTypeService loanTypeService;
    private final InstallmentTypeService installmentTypeService;
    private final CustomerService customerService;
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
                .approvalStatus(loanTransaction.getApprovalStatus().name())
                .loanTransactionDetails(!loanTransaction.getLoanTransactionDetails().isEmpty() ? loanTransaction.getLoanTransactionDetails() : new ArrayList<>())
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

    @Override
    public LoanTransactionResponse findById(String id) {
        LoanTransaction loanTransaction = getLoanTransactionOrThrow(id);
        return mapToResponse(loanTransaction);
    }

}
