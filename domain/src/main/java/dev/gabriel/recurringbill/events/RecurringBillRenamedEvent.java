package dev.gabriel.recurringbill.events;

import lombok.Getter;

import java.util.UUID;

@Getter
public class RecurringBillRenamedEvent extends RecurringBillEvent {
    private final String name;

    public RecurringBillRenamedEvent(UUID recurringBillId, Long version, String name) {
        super(recurringBillId, version);
        this.name = name;
    }
}
