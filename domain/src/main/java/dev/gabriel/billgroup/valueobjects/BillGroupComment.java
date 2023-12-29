package dev.gabriel.billgroup.valueobjects;

import dev.gabriel.billgroup.exceptions.BillGroupValidationException;
import dev.gabriel.shared.valueobjects.ValueObject;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class BillGroupComment extends ValueObject {
    private final String value;

    private BillGroupComment(String value) {
        validate(value);
        this.value = value;
    }

    public static BillGroupComment create(String value) {
        return new BillGroupComment(value);
    }

    private void validate(String value) {
        if(value == null || value.length() > 1510) {
            throw new BillGroupValidationException("Comment", "Comment must have between 0 and 1510 characters.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}
