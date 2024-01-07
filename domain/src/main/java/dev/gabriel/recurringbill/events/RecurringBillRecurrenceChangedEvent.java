package dev.gabriel.recurringbill.events;

import lombok.Getter;

@Getter
public class RecurringBillRecurrenceChangedEvent extends RecurringBillEvent {
    private final Long recurrence;

    public RecurringBillRecurrenceChangedEvent(String recurringBillId, Long version, Long recurrence) {
        super(recurringBillId, version);
        this.recurrence = recurrence;
    }
}
