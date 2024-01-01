package dev.gabriel.reminder.events;

import dev.gabriel.reminder.valueobjects.ReminderId;

public class ReminderRenamedEvent extends ReminderEvent {
    public ReminderRenamedEvent(ReminderId reminderId) {
        super(reminderId);
    }
}
