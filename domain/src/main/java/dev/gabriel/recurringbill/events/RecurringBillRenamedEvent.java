package dev.gabriel.recurringbill.events;

import dev.gabriel.recurringbill.valueobjects.RecurringBillId;

public class RecurringBillRenamedEvent extends RecurringBillEvent {
    public RecurringBillRenamedEvent(RecurringBillId recurringBillId) {
        super(recurringBillId);
    }
}
