package dev.gabriel.recurringbill.events;

import dev.gabriel.recurringbill.valueobjects.RecurringBillId;

public class RecurringBillCurrencyCodeChangedEvent extends RecurringBillEvent {
    public RecurringBillCurrencyCodeChangedEvent(RecurringBillId recurringBillId) {
        super(recurringBillId);
    }
}
