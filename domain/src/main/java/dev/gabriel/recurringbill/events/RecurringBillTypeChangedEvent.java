package dev.gabriel.recurringbill.events;

import dev.gabriel.recurringbill.valueobjects.RecurringBillId;

public class RecurringBillTypeChangedEvent extends RecurringBillEvent {
    public RecurringBillTypeChangedEvent(RecurringBillId recurringBillId) {
        super(recurringBillId);
    }
}
