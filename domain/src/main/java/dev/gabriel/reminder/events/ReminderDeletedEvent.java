package dev.gabriel.reminder.events;

import dev.gabriel.reminder.valueobjects.ReminderId;

public class ReminderDeletedEvent extends ReminderEvent {
    public ReminderDeletedEvent(ReminderId reminderId) {
        super(reminderId);
    }
}
