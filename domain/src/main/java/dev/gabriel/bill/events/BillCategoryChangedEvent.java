package dev.gabriel.bill.events;

import lombok.Getter;

import java.util.UUID;

@Getter
public class BillCategoryChangedEvent extends BillEvent {
    private final UUID categoryId;

    public BillCategoryChangedEvent(UUID billId, Long version, UUID categoryId) {
        super(billId, version);
        this.categoryId = categoryId;
    }
}
