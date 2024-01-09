package dev.gabriel.bill.events;

import lombok.Getter;

import java.util.UUID;

@Getter
public class BillCommentEditedEvent extends BillEvent {
    private final String comment;

    public BillCommentEditedEvent(UUID billId, Long version, String comment) {
        super(billId, version);
        this.comment = comment;
    }
}
