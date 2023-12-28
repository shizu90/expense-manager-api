package dev.gabriel.user.valueobjects;

import dev.gabriel.shared.valueobjects.ValueObject;
import dev.gabriel.user.exceptions.UserValidationException;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class Username extends ValueObject {
    private final String value;

    private Username(String value) {
        validate(value);
        this.value = value;
    }

    public static Username create(String value) {
        return new Username(value);
    }

    private void validate(String value) {
        if(value == null || value.length() < 3 || value.length() > 128) {
            throw new UserValidationException("Username", "Name must have between 3 and 128 characters.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}
