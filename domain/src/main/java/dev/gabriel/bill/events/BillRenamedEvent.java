package dev.gabriel.bill.events;

import dev.gabriel.bill.valueobjects.BillId;

public class BillRenamedEvent extends BillEvent {
    public BillRenamedEvent(BillId billId) {
        super(billId);
    }
}
