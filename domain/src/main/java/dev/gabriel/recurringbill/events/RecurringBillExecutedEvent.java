package dev.gabriel.recurringbill.events;

import dev.gabriel.recurringbill.valueobjects.RecurringBillId;

public class RecurringBillExecutedEvent extends RecurringBillEvent {
    public RecurringBillExecutedEvent(RecurringBillId recurringBillId) {
        super(recurringBillId);
    }
}
