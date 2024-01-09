package dev.gabriel.recurringbill.events;

import java.util.UUID;

public class RecurringBillRestartedEvent extends RecurringBillEvent {
    public RecurringBillRestartedEvent(UUID recurringBillId, Long version) {
        super(recurringBillId, version);
    }
}
