package dev.gabriel.recurringbill.events;

import dev.gabriel.recurringbill.valueobjects.RecurringBillId;

public class RecurringBillRestartedEvent extends RecurringBillEvent {
    public RecurringBillRestartedEvent(RecurringBillId recurringBillId) {
        super(recurringBillId);
    }
}
