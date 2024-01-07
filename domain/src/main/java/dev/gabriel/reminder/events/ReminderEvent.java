package dev.gabriel.reminder.events;

import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;

public class ReminderEvent extends DomainEvent {
    protected ReminderEvent(String reminderId, Long version) {
        super(reminderId, version, Instant.now());
    }
}
