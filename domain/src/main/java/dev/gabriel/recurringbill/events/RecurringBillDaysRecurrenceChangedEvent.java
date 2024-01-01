package dev.gabriel.recurringbill.events;

import dev.gabriel.recurringbill.valueobjects.RecurringBillId;

public class RecurringBillDaysRecurrenceChangedEvent extends RecurringBillEvent {
    public RecurringBillDaysRecurrenceChangedEvent(RecurringBillId recurringBillId) {
        super(recurringBillId);
    }
}
