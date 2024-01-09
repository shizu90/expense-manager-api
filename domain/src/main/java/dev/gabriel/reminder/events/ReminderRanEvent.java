package dev.gabriel.reminder.events;

import java.util.UUID;

public class ReminderRanEvent extends ReminderEvent {
    public ReminderRanEvent(UUID reminderId, Long version) {
        super(reminderId, version);
    }
}
