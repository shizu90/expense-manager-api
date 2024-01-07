package dev.gabriel.recurringbill.valueobjects;

import dev.gabriel.recurringbill.exceptions.RecurringBillValidationException;
import dev.gabriel.shared.valueobjects.ValueObject;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class RecurringBillPeriod extends ValueObject {
    private final Long value;

    private RecurringBillPeriod(Long value) {
        this.value = value;
    }

    public static RecurringBillPeriod create(Long value) {
        return new RecurringBillPeriod(value);
    }

    public static void validate(Long value) {
        if(value == null || value < 0L) {
            throw new RecurringBillValidationException("Period", "Period should not be smaller than 0.");
        }
    }

    public static void validate(Long value, Long recurrence) {
        validate(value);
        if(value < recurrence) {
            throw new RecurringBillValidationException("Max periods", "Max periods must be greater than recurrence.");
        }
    }

    public RecurringBillPeriod increment(Long n) {
        return new RecurringBillPeriod(value + n);
    }

    public RecurringBillPeriod decrement(Long n) {
        return new RecurringBillPeriod(value - n);
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}
