package dev.gabriel.recurringbill.valueobjects;

import dev.gabriel.recurringbill.exceptions.RecurringBillValidationException;
import dev.gabriel.shared.valueobjects.ValueObject;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class RecurringBillRecurrence extends ValueObject {
    private final Long value;

    private RecurringBillRecurrence(Long value) {
        this.value = value;
    }

    public static RecurringBillRecurrence create(Long value) {
        return new RecurringBillRecurrence(value);
    }

    public static void validate(Long value) {
        if(value == null || value <= 0L) {
            throw new RecurringBillValidationException("Recurrence", "Recurrence must be greater than 0.");
        }
    }

    public static void validate(Long value, Long maxPeriods) {
        validate(value);
        if(value > maxPeriods) {
            throw new RecurringBillValidationException("Recurrence", "Recurrence must be smaller than max runs.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}
