package dev.gabriel.shared.valueobjects;

import dev.gabriel.shared.entities.ValueObject;
import dev.gabriel.shared.valueobjects.CalendarDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class CalendarDateTest {
    @Test
    @DisplayName("Should create calendar date successfully.")
    void createCalendarDateTestCase() {
        CalendarDate calendarDate = CalendarDate.create(LocalDate.now());

        Assertions.assertInstanceOf(ValueObject.class, calendarDate);
    }
}
