package dev.gabriel.bill.events;

import dev.gabriel.shared.valueobjects.CurrencyCode;
import lombok.Getter;

@Getter
public class BillCurrencyCodeChangedEvent extends BillEvent {
    private final CurrencyCode currencyCode;

    public BillCurrencyCodeChangedEvent(String billId, Long version, CurrencyCode currencyCode) {
        super(billId, version);
        this.currencyCode = currencyCode;
    }
}
