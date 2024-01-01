package dev.gabriel.bill.events;

import dev.gabriel.bill.valueobjects.BillId;

public class BillCommentEditedEvent extends BillEvent {
    public BillCommentEditedEvent(BillId billId) {
        super(billId);
    }
}
