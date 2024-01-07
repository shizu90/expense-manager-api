package dev.gabriel.shared.services;

import dev.gabriel.shared.valueobjects.Currency;
import dev.gabriel.shared.valueobjects.CurrencyCode;

import java.math.BigDecimal;

public interface CurrencyConversionService extends IDomainService {
    BigDecimal convert(Currency from, CurrencyCode to);
}
