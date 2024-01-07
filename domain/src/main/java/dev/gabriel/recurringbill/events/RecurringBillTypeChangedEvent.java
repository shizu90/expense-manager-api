package dev.gabriel.recurringbill.events;

import dev.gabriel.recurringbill.models.RecurringBillType;
import lombok.Getter;

@Getter
public class RecurringBillTypeChangedEvent extends RecurringBillEvent {
    private final RecurringBillType type;

    public RecurringBillTypeChangedEvent(String recurringBillId, Long version, RecurringBillType type) {
        super(recurringBillId, version);
        this.type = type;
    }
}
