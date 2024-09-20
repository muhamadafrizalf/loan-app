package com.enigma.loan_app.service;

import com.enigma.loan_app.entity.LoanType;

import java.util.List;

public interface LoanTypeService {
    LoanType create(LoanType loanType);
    LoanType findById(String id);
    List<LoanType> findAll();
    LoanType update(LoanType loanType);
    void delete(String id);
}
