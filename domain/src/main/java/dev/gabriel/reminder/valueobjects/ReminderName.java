package dev.gabriel.reminder.valueobjects;

import dev.gabriel.reminder.exceptions.ReminderValidationException;
import dev.gabriel.shared.valueobjects.ValueObject;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class ReminderName extends ValueObject {
    private final String value;

    private ReminderName(String value) {
        this.value = value;
    }

    public static ReminderName create(String value) {
        return new ReminderName(value);
    }

    public static void validate(String value) {
        if(value == null || value.length() > 255 || value.isEmpty()) {
            throw new ReminderValidationException("Name", "Name must have between 1 and 255 characters");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}
