package com.enigma.loan_app.entity;

import com.enigma.loan_app.constant.EInstallmentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_installment_type")
public class InstallmentType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "installment_type")
    @Enumerated(EnumType.STRING)
    private EInstallmentType installmentType;
}