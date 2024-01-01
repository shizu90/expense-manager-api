package dev.gabriel.reminder.events;

import dev.gabriel.reminder.valueobjects.ReminderId;

public class ReminderDescriptionEditedEvent extends ReminderEvent {
    public ReminderDescriptionEditedEvent(ReminderId reminderId) {
        super(reminderId);
    }
}
