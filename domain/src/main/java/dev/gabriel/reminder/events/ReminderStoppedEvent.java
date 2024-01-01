package dev.gabriel.reminder.events;

import dev.gabriel.reminder.valueobjects.ReminderId;

public class ReminderStoppedEvent extends ReminderEvent {
    public ReminderStoppedEvent(ReminderId reminderId) {
        super(reminderId);
    }
}
