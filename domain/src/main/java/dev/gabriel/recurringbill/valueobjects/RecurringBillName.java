package dev.gabriel.recurringbill.valueobjects;

import dev.gabriel.recurringbill.exceptions.RecurringBillValidationException;
import dev.gabriel.shared.valueobjects.ValueObject;

import java.util.Arrays;
import java.util.List;

public class RecurringBillName extends ValueObject {
    private final String value;

    private RecurringBillName(String value) {
        validate(value);
        this.value = value;
    }

    public static RecurringBillName create(String value) {
        return new RecurringBillName(value);
    }

    private void validate(String value) {
        if(value == null || value.isEmpty() || value.length() > 255) {
            throw new RecurringBillValidationException("Name", "Name must have between 1 and 255 characters.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}
