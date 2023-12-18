package dev.gabriel.valueobjects;

import dev.gabriel.enums.CurrencyType;
import dev.gabriel.primitives.ValueObject;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class Money extends ValueObject {
    private Double value;
    private CurrencyType currency;

    private Money(Double value, CurrencyType currency) {
        this.value = value;
        this.currency = currency;
    }

    public static Money create(Double value, CurrencyType currency) {
        return new Money(value, currency);
    }

    public Money add(Money other) {
        if(!other.currency.equals(this.currency)) return null;
        this.value += other.value;
        return this;
    }

    public Money add(Double value) {
        this.value += value;
        return this;
    }

    public Money subtract(Money other) {
        if(!other.currency.equals(this.currency)) return null;
        this.value -= other.value;
        return this;
    }

    public Money subtract(Double value) {
        this.value -= value;
        return this;
    }

    public Money multiply(Double value) {
        this.value *= value;
        return this;
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value, currency});
    }
}
