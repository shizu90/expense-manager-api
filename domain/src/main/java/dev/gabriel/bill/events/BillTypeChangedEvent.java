package dev.gabriel.bill.events;

import dev.gabriel.bill.models.BillType;
import dev.gabriel.bill.valueobjects.BillId;
import lombok.Getter;

@Getter
public class BillTypeChangedEvent extends BillEvent {
    private final BillType type;

    public BillTypeChangedEvent(BillId billId, Long version, BillType type) {
        super(billId, version);
        this.type = type;
    }
}
