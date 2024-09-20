package com.enigma.loan_app.repository;

import com.enigma.loan_app.entity.LoanTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanTransactionRepository extends JpaRepository<LoanTransaction, String> {

}
