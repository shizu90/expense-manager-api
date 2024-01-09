package dev.gabriel.reminder.events;

import java.util.UUID;

public class ReminderRestartedEvent extends ReminderEvent {
    public ReminderRestartedEvent(UUID reminderId, Long version) {
        super(reminderId, version);
    }
}
