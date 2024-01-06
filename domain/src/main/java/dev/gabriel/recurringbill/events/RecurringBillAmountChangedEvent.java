package dev.gabriel.recurringbill.events;

import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.shared.valueobjects.Currency;
import lombok.Getter;

@Getter
public class RecurringBillAmountChangedEvent extends RecurringBillEvent {
    private final Currency amount;

    public RecurringBillAmountChangedEvent(RecurringBillId recurringBillId, Long version, Currency amount) {
        super(recurringBillId, version);
        this.amount = amount;
    }
}
