package dev.gabriel.recurringbill.events;

import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;

public class RecurringBillDeletedEvent extends DomainEvent {
    public RecurringBillDeletedEvent(RecurringBillId recurringBillId) {
        super(recurringBillId, Instant.now());
    }
}
