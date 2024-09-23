package com.enigma.loan_app.entity;

import com.enigma.loan_app.constant.EInstallmentType;
import com.enigma.loan_app.constant.Message;
import com.enigma.loan_app.constant.PathDb;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = PathDb.INSTALLMENT_TYPE)
public class InstallmentType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "installment_type")
    @Enumerated(EnumType.STRING)
    private EInstallmentType installmentType;
}
