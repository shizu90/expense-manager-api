package dev.gabriel.recurringbill.events;

import dev.gabriel.category.valueobjects.CategoryId;
import dev.gabriel.recurringbill.valueobjects.RecurringBillId;
import lombok.Getter;

@Getter
public class RecurringBillCategoryChangedEvent extends RecurringBillEvent {
    private final CategoryId categoryId;

    public RecurringBillCategoryChangedEvent(RecurringBillId recurringBillId, Long version, CategoryId categoryId) {
        super(recurringBillId, version);
        this.categoryId = categoryId;
    }
}
