package dev.gabriel.bill.events;

import lombok.Getter;

@Getter
public class BillCommentEditedEvent extends BillEvent {
    private final String comment;

    public BillCommentEditedEvent(String billId, Long version, String comment) {
        super(billId, version);
        this.comment = comment;
    }
}
