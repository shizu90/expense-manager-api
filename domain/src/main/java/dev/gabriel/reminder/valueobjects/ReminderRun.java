package dev.gabriel.reminder.valueobjects;

import dev.gabriel.reminder.exceptions.ReminderValidationException;
import dev.gabriel.shared.valueobjects.ValueObject;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class ReminderRun extends ValueObject {
    private final Long value;

    private ReminderRun(Long value) {
        validate(value);
        this.value = value;
    }

    public static ReminderRun create(Long value) {
        return new ReminderRun(value);
    }

    public ReminderRun increment(long n) {
        return new ReminderRun(value + n);
    }

    private void validate(Long value) {
        if(value == null || value < 0L) {
            throw new ReminderValidationException("Run", "Run must not be smaller than 0.");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}
