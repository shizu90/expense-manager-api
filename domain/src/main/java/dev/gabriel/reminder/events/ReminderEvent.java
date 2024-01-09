package dev.gabriel.reminder.events;

import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public class ReminderEvent extends DomainEvent {
    protected ReminderEvent(UUID reminderId, Long version) {
        super(reminderId, version, Instant.now());
    }
}
