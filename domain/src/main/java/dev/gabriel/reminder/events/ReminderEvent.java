package dev.gabriel.reminder.events;

import dev.gabriel.reminder.valueobjects.ReminderId;
import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;

public class ReminderEvent extends DomainEvent {
    protected ReminderEvent(ReminderId reminderId, Long version) {
        super(reminderId, version, Instant.now());
    }
}
