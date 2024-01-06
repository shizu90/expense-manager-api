package dev.gabriel.recurringbill.events;

import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import dev.gabriel.recurringbill.valueobjects.RecurringBillName;
import lombok.Getter;

@Getter
public class RecurringBillRenamedEvent extends RecurringBillEvent {
    private final RecurringBillName name;

    public RecurringBillRenamedEvent(RecurringBillId recurringBillId, Long version, RecurringBillName name) {
        super(recurringBillId, version);
        this.name = name;
    }
}
