package dev.gabriel.bill.events;

import dev.gabriel.bill.valueobjects.BillComment;
import dev.gabriel.bill.valueobjects.BillId;
import lombok.Getter;

@Getter
public class BillCommentEditedEvent extends BillEvent {
    private final BillComment comment;

    public BillCommentEditedEvent(BillId billId, Long version, BillComment comment) {
        super(billId, version);
        this.comment = comment;
    }
}
