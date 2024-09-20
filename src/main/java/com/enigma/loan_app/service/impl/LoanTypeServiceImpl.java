package com.enigma.loan_app.service.impl;

import com.enigma.loan_app.entity.LoanType;
import com.enigma.loan_app.repository.LoanTypeRepository;
import com.enigma.loan_app.service.LoanTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanTypeServiceImpl implements LoanTypeService {
    private final LoanTypeRepository loanTypeRepository;

    @Override
    public LoanType create(LoanType loanType) {
        return loanTypeRepository.saveAndFlush(loanType);
    }

    @Override
    public LoanType findById(String id) {
        if (id == null || id.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Loan type id cannot be null or empty");
        return loanTypeRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Loan type not found"));
    }

    @Override
    public List<LoanType> findAll() {
        return loanTypeRepository.findAll();
    }

    @Override
    public LoanType update(LoanType loanType) {
        findById(loanType.getId());
        return create(loanType);
    }

    @Override
    public void delete(String id) {
        LoanType loanType = findById(id);
        loanTypeRepository.delete(loanType);
    }
}
