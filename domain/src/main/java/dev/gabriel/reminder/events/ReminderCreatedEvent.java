package dev.gabriel.reminder.events;

import dev.gabriel.reminder.valueobjects.ReminderId;

public class ReminderCreatedEvent extends ReminderEvent {
    public ReminderCreatedEvent(ReminderId reminderId) {
        super(reminderId);
    }
}
