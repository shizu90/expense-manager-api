package dev.gabriel.recurringbill.events;

import dev.gabriel.recurringbill.valueobjects.RecurringBillId;

public class RecurringBillAmountChangedEvent extends RecurringBillEvent {
    public RecurringBillAmountChangedEvent(RecurringBillId recurringBillId) {
        super(recurringBillId);
    }
}
