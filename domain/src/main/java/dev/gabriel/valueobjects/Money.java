package dev.gabriel.valueobjects;

import dev.gabriel.enums.CurrencyType;
import dev.gabriel.exceptions.DifferentCurrencyTypeException;
import dev.gabriel.primitives.ValueObject;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Money extends ValueObject {
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

    public boolean isGreater(Money other) {
        return value.compareTo(other.value) >= 0;
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value, currency});
    }
}
