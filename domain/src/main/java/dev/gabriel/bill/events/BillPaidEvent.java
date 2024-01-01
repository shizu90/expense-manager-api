package dev.gabriel.bill.events;

import dev.gabriel.bill.valueobjects.BillId;

public class BillPaidEvent extends BillEvent {
    public BillPaidEvent(BillId billId) {
        super(billId);
    }
}
