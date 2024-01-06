package dev.gabriel.reminder.events;

import dev.gabriel.reminder.valueobjects.ReminderId;

public class ReminderRanEvent extends ReminderEvent {
    public ReminderRanEvent(ReminderId reminderId, Long version) {
        super(reminderId, version);
    }
}
