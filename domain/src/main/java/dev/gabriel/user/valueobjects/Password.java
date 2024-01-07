package dev.gabriel.user.valueobjects;

import dev.gabriel.shared.valueobjects.ValueObject;
import dev.gabriel.user.exceptions.UserValidationException;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class Password extends ValueObject {
    private final String value;

    private Password(String value) {
        this.value = value;
    }

    public static Password create(String value) {
        return new Password(value);
    }

    public static void validate(String value) {
        if(value == null || value.length() < 6 || value.length() > 24) {
            throw new UserValidationException("Password", "Password must have between 6 and 24 characters.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}
