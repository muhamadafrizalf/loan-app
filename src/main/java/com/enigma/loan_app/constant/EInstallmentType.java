package com.enigma.loan_app.constant;

import lombok.Getter;

@Getter
public enum EInstallmentType {
    ONE_MONTH(1),
    THREE_MONTHS(3),
    SIXTH_MONTHS(6),
    NINE_MONTHS(9),
    TWELVE_MONTHS(12);

    private final int value;

    EInstallmentType(int value) {
        this.value = value;
    }

}
