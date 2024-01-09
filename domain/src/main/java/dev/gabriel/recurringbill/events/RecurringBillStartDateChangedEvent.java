package dev.gabriel.recurringbill.events;

import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class RecurringBillStartDateChangedEvent extends RecurringBillEvent {
    private final LocalDate startDate;

    public RecurringBillStartDateChangedEvent(UUID recurringBillId, Long version, LocalDate startDate) {
        super(recurringBillId, version);
        this.startDate = startDate;
    }
}
