package dev.gabriel.bill.events;

import dev.gabriel.bill.valueobjects.BillId;

public class BillCurrencyCodeChangedEvent extends BillEvent {
    public BillCurrencyCodeChangedEvent(BillId billId) {
        super(billId);
    }
}
