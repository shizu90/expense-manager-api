package dev.gabriel.recurringbill.events;

import lombok.Getter;

import java.util.UUID;

@Getter
public class RecurringBillCategoryChangedEvent extends RecurringBillEvent {
    private final UUID categoryId;

    public RecurringBillCategoryChangedEvent(UUID recurringBillId, Long version, UUID categoryId) {
        super(recurringBillId, version);
        this.categoryId = categoryId;
    }
}
