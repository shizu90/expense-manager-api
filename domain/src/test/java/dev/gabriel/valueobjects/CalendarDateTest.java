package dev.gabriel.valueobjects;

import dev.gabriel.primitives.ValueObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class CalendarDateTest {
    @Test
    @DisplayName("Should create calendar date successfully.")
    void createCalendarDateTestCase() {
        CalendarDate calendarDate = CalendarDate.create(LocalDate.now());

        Assertions.assertInstanceOf(ValueObject.class, calendarDate);
    }
}
