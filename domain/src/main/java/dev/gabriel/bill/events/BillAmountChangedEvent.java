package dev.gabriel.bill.events;

import dev.gabriel.bill.valueobjects.BillId;

public class BillAmountChangedEvent extends BillEvent {
    public BillAmountChangedEvent(BillId billId) {
        super(billId);
    }
}
