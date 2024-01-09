package dev.gabriel.bill.events;

import dev.gabriel.bill.models.BillType;
import lombok.Getter;

import java.util.UUID;

@Getter
public class BillTypeChangedEvent extends BillEvent {
    private final BillType type;

    public BillTypeChangedEvent(UUID billId, Long version, BillType type) {
        super(billId, version);
        this.type = type;
    }
}
