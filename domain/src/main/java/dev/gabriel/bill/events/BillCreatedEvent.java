package dev.gabriel.bill.events;

import dev.gabriel.bill.valueobjects.BillId;

public class BillCreatedEvent extends BillEvent {
    public BillCreatedEvent(BillId billId) {
        super(billId);
    }
}
