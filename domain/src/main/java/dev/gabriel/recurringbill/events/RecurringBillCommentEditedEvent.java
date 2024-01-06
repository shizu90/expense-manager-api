package dev.gabriel.recurringbill.events;

import dev.gabriel.recurringbill.valueobjects.RecurringBillComment;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import lombok.Getter;

@Getter
public class RecurringBillCommentEditedEvent extends RecurringBillEvent {
    private final RecurringBillComment comment;

    public RecurringBillCommentEditedEvent(RecurringBillId recurringBillId, Long version, RecurringBillComment comment) {
        super(recurringBillId, version);
        this.comment = comment;
    }
}
