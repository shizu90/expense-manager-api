package dev.gabriel.recurringbill.events;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class RecurringBillAmountChangedEvent extends RecurringBillEvent {
    private final BigDecimal amount;

    public RecurringBillAmountChangedEvent(UUID recurringBillId, Long version, BigDecimal amount) {
        super(recurringBillId, version);
        this.amount = amount;
    }
}
