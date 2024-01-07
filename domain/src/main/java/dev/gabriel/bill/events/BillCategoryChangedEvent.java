package dev.gabriel.bill.events;

import lombok.Getter;

@Getter
public class BillCategoryChangedEvent extends BillEvent {
    private final String categoryId;

    public BillCategoryChangedEvent(String billId, Long version, String categoryId) {
        super(billId, version);
        this.categoryId = categoryId;
    }
}
