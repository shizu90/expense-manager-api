package dev.gabriel.recurringbill.events;

import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class RecurringBillStartDateChangedEvent extends RecurringBillEvent {
    private final LocalDate startDate;

    public RecurringBillStartDateChangedEvent(RecurringBillId recurringBillId, Long version, LocalDate startDate) {
        super(recurringBillId, version);
        this.startDate = startDate;
    }
}
