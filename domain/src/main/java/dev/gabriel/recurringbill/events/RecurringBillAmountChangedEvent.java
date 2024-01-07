package dev.gabriel.recurringbill.events;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class RecurringBillAmountChangedEvent extends RecurringBillEvent {
    private final BigDecimal amount;

    public RecurringBillAmountChangedEvent(String recurringBillId, Long version, BigDecimal amount) {
        super(recurringBillId, version);
        this.amount = amount;
    }
}
