package dev.gabriel.bill.events;

import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.category.valueobjects.CategoryId;
import lombok.Getter;

@Getter
public class BillCategoryChangedEvent extends BillEvent {
    private final CategoryId categoryId;

    public BillCategoryChangedEvent(BillId billId, Long version, CategoryId categoryId) {
        super(billId, version);
        this.categoryId = categoryId;
    }
}
