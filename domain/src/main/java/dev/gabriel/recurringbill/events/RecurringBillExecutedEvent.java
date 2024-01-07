package dev.gabriel.recurringbill.events;

import lombok.Getter;

@Getter
public class RecurringBillExecutedEvent extends RecurringBillEvent {
    private final Long currentPeriods;

    public RecurringBillExecutedEvent(String recurringBillId, Long version, Long currentPeriods) {
        super(recurringBillId, version);
        this.currentPeriods = currentPeriods;
    }
}
