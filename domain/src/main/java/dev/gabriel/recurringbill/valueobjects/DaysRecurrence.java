package dev.gabriel.recurringbill.valueobjects;

import dev.gabriel.recurringbill.exceptions.RecurringBillValidationException;
import dev.gabriel.shared.valueobjects.ValueObject;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class DaysRecurrence extends ValueObject {
    private final Long value;

    private DaysRecurrence(Long value) {
        validate(value);
        this.value = value;
    }

    public static DaysRecurrence create(Long value) {
        return new DaysRecurrence(value);
    }

    private void validate(Long value) {
        if(value == null || value <= 0L) {
            throw new RecurringBillValidationException("Days Recurrence", "Days recurrence must be greater than 0.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}
