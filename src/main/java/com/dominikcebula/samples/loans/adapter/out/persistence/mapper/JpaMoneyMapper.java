package com.dominikcebula.samples.loans.adapter.out.persistence.mapper;

import org.javamoney.moneta.Money;
import org.mapstruct.Mapper;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface JpaMoneyMapper {
    default Money mapJpaMoneyToMoney(BigDecimal amount, String currency) {
        return Money.of(amount, currency);
    }

    default BigDecimal mapMoneyToJpaMoney(Money money) {
        return new BigDecimal(money.getNumber().toString());
    }
}
