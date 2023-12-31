package dev.gabriel.recurringbill.valueobjects;

import dev.gabriel.recurringbill.exceptions.RecurringBillValidationException;
import dev.gabriel.shared.valueobjects.ValueObject;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class Period extends ValueObject {
    private final Long value;

    private Period(Long value) {
        validate(value);
        this.value = value;
    }

    public static Period create(Long value) {
        return new Period(value);
    }

    private void validate(Long value) {
        if(value == null || value < 0L) {
            throw new RecurringBillValidationException("Period", "Period should not be smaller than 0.");
        }
    }

    public Period increment(Long n) {
        return new Period(value + n);
    }

    public Period decrement(Long n) {
        return new Period(value - n);
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}
