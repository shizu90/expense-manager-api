package dev.gabriel.shared.valueobjects;

import dev.gabriel.shared.exceptions.DifferentCurrencyTypeException;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Getter
public class Currency extends ValueObject implements Comparable<Currency> {
    private final BigDecimal value;
    private final CurrencyCode currencyCode;

    private Currency(BigDecimal value, CurrencyCode currency) {
        this.value = value;
        this.currencyCode = currency;
    }

    public static Currency create(BigDecimal value, CurrencyCode currency) {
        return new Currency(value, currency);
    }

    public Currency add(Currency other) {
        if(!other.currencyCode.equals(this.currencyCode)) throw new DifferentCurrencyTypeException("Given currency type is different.");
        return new Currency(value.add(other.value), this.currencyCode);
    }

    public Currency subtract(Currency other) {
        if(!other.currencyCode.equals(this.currencyCode)) throw new DifferentCurrencyTypeException("Given currency type is different.");
        return new Currency(value.subtract(other.value), this.currencyCode);
    }

    public Currency multiply(double value) {
        return new Currency(this.value.multiply(BigDecimal.valueOf(value)), this.currencyCode);
    }

    @Override
    public int compareTo(Currency other) {
        return value.compareTo(other.value);
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value, currencyCode});
    }
}
