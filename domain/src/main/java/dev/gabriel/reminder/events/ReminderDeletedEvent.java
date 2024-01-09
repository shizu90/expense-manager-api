package dev.gabriel.reminder.events;

import java.util.UUID;

public class ReminderDeletedEvent extends ReminderEvent {
    public ReminderDeletedEvent(UUID reminderId, Long version) {
        super(reminderId, version);
    }
}
