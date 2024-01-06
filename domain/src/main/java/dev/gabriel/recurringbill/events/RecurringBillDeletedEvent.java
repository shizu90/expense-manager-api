package dev.gabriel.recurringbill.events;

import dev.gabriel.recurringbill.valueobjects.RecurringBillId;

public class RecurringBillDeletedEvent extends RecurringBillEvent {
    public RecurringBillDeletedEvent(RecurringBillId recurringBillId, Long version) {
        super(recurringBillId, version);
    }
}
