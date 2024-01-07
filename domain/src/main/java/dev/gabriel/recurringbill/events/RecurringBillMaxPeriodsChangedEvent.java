package dev.gabriel.recurringbill.events;

import lombok.Getter;

@Getter
public class RecurringBillMaxPeriodsChangedEvent extends RecurringBillEvent {
    private final Long maxPeriods;

    public RecurringBillMaxPeriodsChangedEvent(String recurringBillId, Long version, Long maxPeriods) {
        super(recurringBillId, version);
        this.maxPeriods = maxPeriods;
    }
}
