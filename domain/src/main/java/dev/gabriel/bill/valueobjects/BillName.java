package dev.gabriel.bill.valueobjects;

import dev.gabriel.bill.exceptions.BillValidationException;
import dev.gabriel.shared.valueobjects.ValueObject;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class BillName extends ValueObject {
    private final String value;

    private BillName(String value) {
        validate(value);
        this.value = value;
    }

    public static BillName create(String value) {
        return new BillName(value);
    }

    private void validate(String value) {
        if(value == null || value.isEmpty() || value.length() > 255) {
            throw new BillValidationException("Name", "Name must have between 3 and 255 characters.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}
