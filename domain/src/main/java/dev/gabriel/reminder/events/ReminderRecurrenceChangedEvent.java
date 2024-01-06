package dev.gabriel.reminder.events;

import dev.gabriel.reminder.valueobjects.ReminderId;
import dev.gabriel.reminder.valueobjects.ReminderRecurrence;
import lombok.Getter;

@Getter
public class ReminderRecurrenceChangedEvent extends ReminderEvent {
    private final ReminderRecurrence recurrence;

    public ReminderRecurrenceChangedEvent(ReminderId reminderId, Long version, ReminderRecurrence recurrence) {
        super(reminderId, version);
        this.recurrence = recurrence;
    }
}
