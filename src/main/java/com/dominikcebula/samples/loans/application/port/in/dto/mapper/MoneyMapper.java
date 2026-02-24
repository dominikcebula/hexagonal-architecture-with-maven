package com.dominikcebula.samples.loans.application.port.in.dto.mapper;

import com.dominikcebula.samples.loans.application.port.in.dto.MoneyDTO;
import org.javamoney.moneta.Money;
import org.mapstruct.Mapper;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface MoneyMapper {
    default MoneyDTO mapMoneyToMoneyDTO(Money money) {
        return new MoneyDTO(
                new BigDecimal(money.getNumberStripped().toPlainString()),
                money.getCurrency().getCurrencyCode()
        );
    }

    default Money mapMoneyDTOToMoney(MoneyDTO money) {
        return Money.of(
                money.amount(),
                money.currency()
        );
    }
}
