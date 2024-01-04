package dev.gabriel.recurringbill.events;

import dev.gabriel.recurringbill.valueobjects.RecurringBillId;

public class RecurringBillCategoryChangedEvent extends RecurringBillEvent {
    public RecurringBillCategoryChangedEvent(RecurringBillId recurringBillId) {
        super(recurringBillId);
    }
}
