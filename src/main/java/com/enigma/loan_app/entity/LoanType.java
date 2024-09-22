package com.enigma.loan_app.entity;

import com.enigma.loan_app.constant.Message;
import com.enigma.loan_app.constant.PathDb;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = PathDb.LOAN_TYPE)
public class LoanType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = Message.TYPE_IS_REQUIRED)
    @Column
    private String type;

    @NotBlank(message = Message.MAX_LOAN_IS_REQUIRED)
    @Positive(message = Message.MAX_LOAN_MUST_POSITIVE)
    @Column(name = "max_loan")
    private Double maxLoan;
}
