package dev.gabriel.recurringbill.events;

import lombok.Getter;

import java.util.UUID;

@Getter
public class RecurringBillExecutedEvent extends RecurringBillEvent {
    private final Long currentPeriods;

    public RecurringBillExecutedEvent(UUID recurringBillId, Long version, Long currentPeriods) {
        super(recurringBillId, version);
        this.currentPeriods = currentPeriods;
    }
}
