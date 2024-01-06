package dev.gabriel.bill.events;

import dev.gabriel.bill.valueobjects.BillId;

public class BillDeletedEvent extends BillEvent {
    public BillDeletedEvent(BillId billId, Long version) {
        super(billId, version);
    }
}
