package dev.gabriel.recurringbill.events;

import lombok.Getter;

import java.util.UUID;

@Getter
public class RecurringBillRecurrenceChangedEvent extends RecurringBillEvent {
    private final Long recurrence;

    public RecurringBillRecurrenceChangedEvent(UUID recurringBillId, Long version, Long recurrence) {
        super(recurringBillId, version);
        this.recurrence = recurrence;
    }
}
