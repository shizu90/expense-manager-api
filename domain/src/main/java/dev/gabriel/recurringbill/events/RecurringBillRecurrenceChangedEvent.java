package dev.gabriel.recurringbill.events;

import dev.gabriel.recurringbill.valueobjects.RecurringBillRecurrence;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import lombok.Getter;

@Getter
public class RecurringBillRecurrenceChangedEvent extends RecurringBillEvent {
    private final RecurringBillRecurrence recurrence;

    public RecurringBillRecurrenceChangedEvent(RecurringBillId recurringBillId, Long version, RecurringBillRecurrence recurrence) {
        super(recurringBillId, version);
        this.recurrence = recurrence;
    }
}
