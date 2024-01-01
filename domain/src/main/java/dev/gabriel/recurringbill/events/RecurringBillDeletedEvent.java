package dev.gabriel.recurringbill.events;

import dev.gabriel.recurringbill.valueobjects.RecurringBillId;

public class RecurringBillDeletedEvent extends RecurringBillEvent {
    public RecurringBillDeletedEvent(RecurringBillId recurringBillId) {
        super(recurringBillId);
    }
}
