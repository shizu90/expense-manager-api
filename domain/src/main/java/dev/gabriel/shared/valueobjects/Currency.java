package dev.gabriel.shared.valueobjects;

import dev.gabriel.shared.exceptions.DifferentCurrencyTypeException;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Getter
public class Currency extends ValueObject implements Comparable<Currency> {
    private final BigDecimal value;
    private final CurrencyType currencyType;

    private Currency(BigDecimal value, CurrencyType currency) {
        this.value = value;
        this.currencyType = currency;
    }

    public static Currency create(BigDecimal value, CurrencyType currency) {
        return new Currency(value, currency);
    }

    public Currency add(Currency other) {
        if(!other.currencyType.equals(this.currencyType)) throw new DifferentCurrencyTypeException("Given currency type is different.");
        return new Currency(value.add(other.value), this.currencyType);
    }

    public Currency subtract(Currency other) {
        if(!other.currencyType.equals(this.currencyType)) throw new DifferentCurrencyTypeException("Given currency type is different.");
        return new Currency(value.subtract(other.value), this.currencyType);
    }

    public Currency multiply(double value) {
        return new Currency(this.value.multiply(BigDecimal.valueOf(value)), this.currencyType);
    }

    @Override
    public int compareTo(Currency other) {
        return value.compareTo(other.value);
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value, currencyType});
    }
}
