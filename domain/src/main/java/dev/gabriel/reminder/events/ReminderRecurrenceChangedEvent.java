package dev.gabriel.reminder.events;

import dev.gabriel.reminder.valueobjects.ReminderId;

public class ReminderRecurrenceChangedEvent extends ReminderEvent {
    public ReminderRecurrenceChangedEvent(ReminderId reminderId) {
        super(reminderId);
    }
}
