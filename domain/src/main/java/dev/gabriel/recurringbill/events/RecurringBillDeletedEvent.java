package dev.gabriel.recurringbill.events;

public class RecurringBillDeletedEvent extends RecurringBillEvent {
    public RecurringBillDeletedEvent(String recurringBillId, Long version) {
        super(recurringBillId, version);
    }
}
