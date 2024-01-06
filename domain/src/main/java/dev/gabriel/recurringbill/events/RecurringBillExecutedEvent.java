package dev.gabriel.recurringbill.events;

import dev.gabriel.recurringbill.valueobjects.Period;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import lombok.Getter;

@Getter
public class RecurringBillExecutedEvent extends RecurringBillEvent {
    private final Period currentPeriods;

    public RecurringBillExecutedEvent(RecurringBillId recurringBillId, Long version, Period currentPeriods) {
        super(recurringBillId, version);
        this.currentPeriods = currentPeriods;
    }
}
