package dev.gabriel.recurringbill.events;

import dev.gabriel.shared.valueobjects.CurrencyCode;
import lombok.Getter;

import java.util.UUID;

@Getter
public class RecurringBillCurrencyCodeChangedEvent extends RecurringBillEvent {
    private final CurrencyCode currencyCode;

    public RecurringBillCurrencyCodeChangedEvent(UUID recurringBillId, Long version, CurrencyCode currencyCode) {
        super(recurringBillId, version);
        this.currencyCode = currencyCode;
    }
}
