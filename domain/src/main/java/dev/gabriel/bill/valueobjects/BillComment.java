package dev.gabriel.bill.valueobjects;

import dev.gabriel.bill.exceptions.BillValidationException;
import dev.gabriel.shared.valueobjects.ValueObject;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class BillComment extends ValueObject {
    private final String value;

    private BillComment(String value) {
        validate(value);
        this.value = value;
    }

    public static BillComment create(String value) {
        return new BillComment(value);
    }

    private void validate(String value) {
        if(value == null || value.length() > 1510) {
            throw new BillValidationException("Comment", "Comment must have between 0 and 1510 characters.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}
