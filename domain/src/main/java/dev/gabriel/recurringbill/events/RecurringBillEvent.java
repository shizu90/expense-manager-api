package dev.gabriel.recurringbill.events;

import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;

public class RecurringBillEvent extends DomainEvent {
    protected RecurringBillEvent(String recurringBillId, Long version) {
        super(recurringBillId, version, Instant.now());
    }
}
