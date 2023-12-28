package dev.gabriel.user.valueobjects;

import dev.gabriel.shared.valueobjects.ValueObject;
import dev.gabriel.user.exceptions.UserValidationException;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class Email extends ValueObject {
    private final String value;

    private Email(String value) {
        validate(value);
        this.value = value;
    }

    public static Email create(String value) {
        return new Email(value);
    }

    private void validate(String value) {
        if(value == null || !value.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new UserValidationException("Email", "Not a valid email address.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}
