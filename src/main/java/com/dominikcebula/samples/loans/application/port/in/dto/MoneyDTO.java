package com.dominikcebula.samples.loans.application.port.in.dto;

import java.math.BigDecimal;

public record MoneyDTO(
        BigDecimal amount,
        String currency
) {
}
