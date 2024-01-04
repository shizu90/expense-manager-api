package dev.gabriel.recurringbill.valueobjects;

import dev.gabriel.recurringbill.exceptions.RecurringBillValidationException;
import dev.gabriel.shared.valueobjects.ValueObject;

import java.util.Arrays;
import java.util.List;

public class RecurringBillComment extends ValueObject {
    private final String value;

    private RecurringBillComment(String value) {
        validate(value);
        this.value = value;
    }

    public static RecurringBillComment create(String value) {
        return new RecurringBillComment(value);
    }

    private void validate(String value) {
        if(value == null || value.length() > 1510) {
            throw new RecurringBillValidationException("Comment", "Comment must have between 0 and 1510 characters.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}
