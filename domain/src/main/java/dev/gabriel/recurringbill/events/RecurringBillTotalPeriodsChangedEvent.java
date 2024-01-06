package dev.gabriel.recurringbill.events;

import dev.gabriel.recurringbill.valueobjects.Period;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import lombok.Getter;

@Getter
public class RecurringBillTotalPeriodsChangedEvent extends RecurringBillEvent {
    private final Period totalPeriods;

    public RecurringBillTotalPeriodsChangedEvent(RecurringBillId recurringBillId, Long version, Period totalPeriods) {
        super(recurringBillId, version);
        this.totalPeriods = totalPeriods;
    }
}
