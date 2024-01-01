package dev.gabriel.bill.events;

import dev.gabriel.bill.valueobjects.BillId;

public class BillCategoryChangedEvent extends BillEvent {
    public BillCategoryChangedEvent(BillId billId) {
        super(billId);
    }
}
