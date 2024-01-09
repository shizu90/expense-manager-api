package dev.gabriel.reminder.events;

import java.util.UUID;

public class ReminderStoppedEvent extends ReminderEvent {
    public ReminderStoppedEvent(UUID reminderId, Long version) {
        super(reminderId, version);
    }
}
