package dev.gabriel.shared.valueobjects;

import dev.gabriel.shared.exceptions.DifferentCurrencyTypeException;
import dev.gabriel.shared.entities.ValueObject;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Money extends ValueObject implements Comparable<Money> {
    private final BigDecimal value;
    private final CurrencyType currency;

    private Money(BigDecimal value, CurrencyType currency) {
        this.value = value;
        this.currency = currency;
    }

    public static Money create(BigDecimal value, CurrencyType currency) {
        return new Money(value, currency);
    }

    public Money add(Money other) {
        if(!other.currency.equals(this.currency)) throw new DifferentCurrencyTypeException("Given currency type is different.");
        return new Money(value.add(other.value), this.currency);
    }

    public Money subtract(Money other) {
        if(!other.currency.equals(this.currency)) throw new DifferentCurrencyTypeException("Given currency type is different.");
        return new Money(value.subtract(other.value), this.currency);
    }

    public Money multiply(double value) {
        return new Money(this.value.multiply(BigDecimal.valueOf(value)), this.currency);
    }

    @Override
    public int compareTo(Money other) {
        return value.compareTo(other.value);
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value, currency});
    }
}
