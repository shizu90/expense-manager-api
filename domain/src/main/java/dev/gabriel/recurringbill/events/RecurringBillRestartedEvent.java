package dev.gabriel.recurringbill.events;

public class RecurringBillRestartedEvent extends RecurringBillEvent {
    public RecurringBillRestartedEvent(String recurringBillId, Long version) {
        super(recurringBillId, version);
    }
}
