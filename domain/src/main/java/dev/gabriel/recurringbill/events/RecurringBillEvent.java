package dev.gabriel.recurringbill.events;

import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public class RecurringBillEvent extends DomainEvent {
    protected RecurringBillEvent(UUID recurringBillId, Long version) {
        super(recurringBillId, version, Instant.now());
    }
}
