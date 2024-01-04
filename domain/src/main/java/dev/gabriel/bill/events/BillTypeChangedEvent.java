package dev.gabriel.bill.events;

import dev.gabriel.bill.valueobjects.BillId;

public class BillTypeChangedEvent extends BillEvent {
    public BillTypeChangedEvent(BillId billId) {
        super(billId);
    }
}
