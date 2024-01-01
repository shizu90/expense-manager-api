package dev.gabriel.recurringbill.events;

import dev.gabriel.recurringbill.valueobjects.RecurringBillId;

public class RecurringBillTotalPeriodsChangedEvent extends RecurringBillEvent {
    public RecurringBillTotalPeriodsChangedEvent(RecurringBillId recurringBillId) {
        super(recurringBillId);
    }
}
