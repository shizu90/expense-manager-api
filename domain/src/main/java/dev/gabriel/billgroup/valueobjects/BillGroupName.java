package dev.gabriel.billgroup.valueobjects;

import dev.gabriel.billgroup.exceptions.BillGroupValidationException;
import dev.gabriel.shared.valueobjects.ValueObject;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class BillGroupName extends ValueObject {
    private final String value;

    private BillGroupName(String value) {
        validate(value);
        this.value = value;
    }

    public static BillGroupName create(String value) {
        return new BillGroupName(value);
    }

    private void validate(String value) {
        if(value == null || value.length() < 3 || value.length() > 255) {
            throw new BillGroupValidationException("Name", "Name must have between 3 and 255 characters.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}
