package dev.gabriel.recurringbill.events;

import lombok.Getter;

import java.util.UUID;

@Getter
public class RecurringBillCommentEditedEvent extends RecurringBillEvent {
    private final String comment;

    public RecurringBillCommentEditedEvent(UUID recurringBillId, Long version, String comment) {
        super(recurringBillId, version);
        this.comment = comment;
    }
}
