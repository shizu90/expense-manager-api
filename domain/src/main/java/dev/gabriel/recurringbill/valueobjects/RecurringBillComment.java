package dev.gabriel.recurringbill.valueobjects;

import dev.gabriel.recurringbill.exceptions.RecurringBillValidationException;
import dev.gabriel.shared.valueobjects.ValueObject;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class RecurringBillComment extends ValueObject {
    private final String value;

    private RecurringBillComment(String value) {
        this.value = value;
    }

    public static RecurringBillComment create(String value) {
        return new RecurringBillComment(value);
    }

    public static void validate(String value) {
        if(value == null || value.length() > 1510) {
            throw new RecurringBillValidationException("Comment", "Comment must have between 0 and 1510 characters.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}
