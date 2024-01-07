package dev.gabriel.reminder.valueobjects;

import dev.gabriel.reminder.exceptions.ReminderValidationException;
import dev.gabriel.shared.valueobjects.ValueObject;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class ReminderDescription extends ValueObject {
    private final String value;

    private ReminderDescription(String value) {
        this.value = value;
    }

    public static ReminderDescription create(String value) {
        return new ReminderDescription(value);
    }

    public static void validate(String value) {
        if(value == null || value.isEmpty() || value.length() > 1510) {
            throw new ReminderValidationException("Description", "Description must have between 1 and 1510 characters.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}
