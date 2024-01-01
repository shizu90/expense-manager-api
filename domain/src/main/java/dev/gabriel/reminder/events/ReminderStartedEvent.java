package dev.gabriel.reminder.events;

import dev.gabriel.reminder.valueobjects.ReminderId;

public class ReminderStartedEvent extends ReminderEvent {
    public ReminderStartedEvent(ReminderId reminderId) {
        super(reminderId);
    }
}
