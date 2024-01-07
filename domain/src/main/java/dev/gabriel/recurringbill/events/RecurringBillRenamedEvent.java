package dev.gabriel.recurringbill.events;

import lombok.Getter;

@Getter
public class RecurringBillRenamedEvent extends RecurringBillEvent {
    private final String name;

    public RecurringBillRenamedEvent(String recurringBillId, Long version, String name) {
        super(recurringBillId, version);
        this.name = name;
    }
}
