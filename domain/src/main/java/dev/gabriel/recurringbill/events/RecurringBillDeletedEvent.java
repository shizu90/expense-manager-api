package dev.gabriel.recurringbill.events;

import java.util.UUID;

public class RecurringBillDeletedEvent extends RecurringBillEvent {
    public RecurringBillDeletedEvent(UUID recurringBillId, Long version) {
        super(recurringBillId, version);
    }
}
