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
        this.value = value;
    }

    public static ReminderRun create(Long value) {
        return new ReminderRun(value);
    }

    public ReminderRun increment(long n) {
        return new ReminderRun(value + n);
    }

    public static void validate(Long value) {
        if(value == null || value < 0L) {
            throw new ReminderValidationException("Run", "Run must not be smaller than 0.");
        }
    }

    public static void validate(Long value, Long recurrence) {
        validate(value);
        if(value < recurrence) {
            throw new ReminderValidationException("Max runs", "Max runs must be greater than recurrence");
        }
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}
