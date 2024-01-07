package dev.gabriel.recurringbill.events;

import dev.gabriel.shared.valueobjects.CurrencyCode;
import lombok.Getter;

@Getter
public class RecurringBillCurrencyCodeChangedEvent extends RecurringBillEvent {
    private final CurrencyCode currencyCode;

    public RecurringBillCurrencyCodeChangedEvent(String recurringBillId, Long version, CurrencyCode currencyCode) {
        super(recurringBillId, version);
        this.currencyCode = currencyCode;
    }
}
