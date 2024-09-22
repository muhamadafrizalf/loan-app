package com.enigma.loan_app.service.impl;

import com.enigma.loan_app.constant.Message;
import com.enigma.loan_app.entity.LoanType;
import com.enigma.loan_app.repository.LoanTypeRepository;
import com.enigma.loan_app.service.LoanTypeService;
import com.enigma.loan_app.util.ValidationUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanTypeServiceImpl implements LoanTypeService {
    private final LoanTypeRepository loanTypeRepository;
    private final ValidationUtil validationUtil;

    private LoanType getLoanTypeOrThrow(String id) {
        if (id == null || id.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Message.LOAN_TYPE_ID_IS_EMPTY);
        return loanTypeRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, Message.LOAN_TYPE_NOT_FOUND));
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public LoanType create(LoanType loanType) {
        validationUtil.validate(loanType);
        return loanTypeRepository.saveAndFlush(loanType);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    @Override
    public LoanType findById(String id) {
        return getLoanTypeOrThrow(id);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    @Override
    public List<LoanType> findAll() {
        return loanTypeRepository.findAll();
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public LoanType update(LoanType loanType) {
        validationUtil.validate(loanType);
        LoanType updatedLoanType = getLoanTypeOrThrow(loanType.getId());
        updatedLoanType.setType(loanType.getType());
        updatedLoanType.setMaxLoan(loanType.getMaxLoan());
        return loanTypeRepository.saveAndFlush(updatedLoanType);
    }

    @Transactional(rollbackOn = Exception.class)
    @Override
    public void delete(String id) {
        getLoanTypeOrThrow(id);
        loanTypeRepository.deleteById(id);
    }
}
