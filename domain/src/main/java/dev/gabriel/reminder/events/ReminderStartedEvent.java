package dev.gabriel.reminder.events;

import java.util.UUID;

public class ReminderStartedEvent extends ReminderEvent {
    public ReminderStartedEvent(UUID reminderId, Long version) {
        super(reminderId, version);
    }
}
