package dev.gabriel.recurringbill.events;

import dev.gabriel.recurringbill.valueobjects.RecurringBillId;

public class RecurringBillCreatedEvent extends RecurringBillEvent {
    public RecurringBillCreatedEvent(RecurringBillId recurringBillId) {
        super(recurringBillId);
    }
}
