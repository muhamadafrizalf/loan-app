package com.enigma.loan_app.entity;

import com.enigma.loan_app.constant.ELoanStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "trx_loan_detail")
public class LoanTransactionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    @Column
    private Double nominal;

    @ManyToOne
    @JoinColumn(name = "loan_transaction_id")
    private LoanTransaction loanTransaction;

    @Column(name = "loan_status")
    @Enumerated(EnumType.STRING)
    private ELoanStatus loanStatus;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
