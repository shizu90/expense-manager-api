package dev.gabriel.reminder.valueobjects;

import dev.gabriel.reminder.exceptions.ReminderValidationException;
import dev.gabriel.shared.valueobjects.ValueObject;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class ReminderRecurrence extends ValueObject {
    private final Long value;

    private ReminderRecurrence(Long value) {
        validate(value);
        this.value = value;
    }

    public static ReminderRecurrence create(Long value) {
        return new ReminderRecurrence(value);
    }

    private void validate(Long value) {
        if(value == null || value <= 0L) {
            throw new ReminderValidationException("Recurrence", "Recurrence must be greater than 0.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}
