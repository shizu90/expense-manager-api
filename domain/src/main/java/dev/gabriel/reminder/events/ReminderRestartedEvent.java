package dev.gabriel.reminder.events;

import dev.gabriel.reminder.valueobjects.ReminderId;

public class ReminderRestartedEvent extends ReminderEvent {
    public ReminderRestartedEvent(ReminderId reminderId, Long version) {
        super(reminderId, version);
    }
}
