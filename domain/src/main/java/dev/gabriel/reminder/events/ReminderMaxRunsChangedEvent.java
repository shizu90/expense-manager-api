package dev.gabriel.reminder.events;

import dev.gabriel.reminder.valueobjects.ReminderId;

public class ReminderMaxRunsChangedEvent extends ReminderEvent {
    public ReminderMaxRunsChangedEvent(ReminderId reminderId) {
        super(reminderId);
    }
}
