package dev.gabriel.recurringbill.events;

import lombok.Getter;

import java.util.UUID;

@Getter
public class RecurringBillMaxPeriodsChangedEvent extends RecurringBillEvent {
    private final Long maxPeriods;

    public RecurringBillMaxPeriodsChangedEvent(UUID recurringBillId, Long version, Long maxPeriods) {
        super(recurringBillId, version);
        this.maxPeriods = maxPeriods;
    }
}
