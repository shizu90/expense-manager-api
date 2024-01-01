package dev.gabriel.recurringbill.events;

import dev.gabriel.recurringbill.valueobjects.RecurringBillId;

public class RecurringBillStartDateChangedEvent extends RecurringBillEvent {
    public RecurringBillStartDateChangedEvent(RecurringBillId recurringBillId) {
        super(recurringBillId);
    }
}
