package dev.gabriel.recurringbill.events;

import lombok.Getter;

@Getter
public class RecurringBillCommentEditedEvent extends RecurringBillEvent {
    private final String comment;

    public RecurringBillCommentEditedEvent(String recurringBillId, Long version, String comment) {
        super(recurringBillId, version);
        this.comment = comment;
    }
}
