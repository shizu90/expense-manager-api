package dev.gabriel.recurringbill.events;

import lombok.Getter;

@Getter
public class RecurringBillCategoryChangedEvent extends RecurringBillEvent {
    private final String categoryId;

    public RecurringBillCategoryChangedEvent(String recurringBillId, Long version, String categoryId) {
        super(recurringBillId, version);
        this.categoryId = categoryId;
    }
}
