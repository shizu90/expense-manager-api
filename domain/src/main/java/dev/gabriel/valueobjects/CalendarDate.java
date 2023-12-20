package dev.gabriel.valueobjects;

import dev.gabriel.primitives.ValueObject;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CalendarDate extends ValueObject {
    private final LocalDate value;

    private CalendarDate(LocalDate value) {
        this.value = value;
    }

    public static CalendarDate create(LocalDate value) {
        return new CalendarDate(value);
    }

    public int getDay() {
        return value.getDayOfMonth();
    }

    public int getMonth() {
        return value.getMonthValue();
    }

    public int getYear() {
        return value.getYear();
    }

    public CalendarDate addDays(long days) {
        return new CalendarDate(value.plusDays(days));
    }

    @Override
    public List<Object> getAtomicValues() {
        return Arrays.asList(new Object[] {value});
    }
}
