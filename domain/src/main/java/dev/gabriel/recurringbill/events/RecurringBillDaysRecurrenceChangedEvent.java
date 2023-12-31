package dev.gabriel.recurringbill.events;

import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;

public class RecurringBillDaysRecurrenceChangedEvent extends DomainEvent {
    public RecurringBillDaysRecurrenceChangedEvent(RecurringBillId recurringBillId) {
        super(recurringBillId, Instant.now());
    }
}
