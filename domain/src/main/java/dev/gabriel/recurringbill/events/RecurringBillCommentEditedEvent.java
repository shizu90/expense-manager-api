package dev.gabriel.recurringbill.events;

import dev.gabriel.recurringbill.valueobjects.RecurringBillId;

public class RecurringBillCommentEditedEvent extends RecurringBillEvent {
    public RecurringBillCommentEditedEvent(RecurringBillId recurringBillId) {
        super(recurringBillId);
    }
}
