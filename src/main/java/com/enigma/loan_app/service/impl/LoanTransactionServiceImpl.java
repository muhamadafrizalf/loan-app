package com.enigma.loan_app.service.impl;

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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoanTransactionServiceImpl implements LoanTransactionService {
    private final LoanTransactionRepository loanTransactionRepository;
    private final LoanTypeService loanTypeService;
    private final InstallmentTypeService installmentTypeService;
    private final CustomerService customerService;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public LoanTransactionResponse create(LoanTransactionRequest loanTransactionRequest) {
        LoanTransaction loanTransaction = new LoanTransaction();

        LoanType loanType = loanTypeService.findById(loanTransactionRequest.getLoanTypeId());
        loanTransaction.setLoanType(loanType);

        InstallmentType installmentType = installmentTypeService.findById(loanTransactionRequest.getInstallmentTypeId());
        loanTransaction.setInstalmentType(installmentType);

        Customer customer = customerService.findById(loanTransactionRequest.getCustomerId());
        loanTransaction.setCustomer(customer);

        loanTransaction.setNominal(loanTransactionRequest.getNominal());
        loanTransaction.setCreatedAt(LocalDateTime.now());

        loanTransactionRepository.saveAndFlush(loanTransaction);

        return LoanTransactionResponse.builder()
                .id(loanTransaction.getId())
                .loanTypeId(loanType.getId())
                .installmentTypeId(installmentType.getId())
                .customerId(customer.getId())
                .nominal(loanTransactionRequest.getNominal())
                .createdAt(loanTransaction.getCreatedAt())
                .build();
    }
}
