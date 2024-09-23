package com.enigma.loan_app.entity;

import com.enigma.loan_app.constant.EApprovalStatus;
import com.enigma.loan_app.constant.PathDb;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = PathDb.LOAN_TRANSACTION)
public class LoanTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "loan_type_id")
    private LoanType loanType;

    @ManyToOne
    @JoinColumn(name = "installment_type_id")
    private InstallmentType instalmentType;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column
    private Double nominal;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @Column(name = "approved_by")
    private String approvedBy;

    @Column(name = "approval_status")
    @Enumerated(EnumType.STRING)
    private EApprovalStatus approvalStatus;

    @OneToMany(mappedBy = "loanTransaction")
    @Cascade(org.hibernate.annotations.CascadeType.MERGE)
    private List<LoanTransactionDetail> loanTransactionDetails;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
