package dev.gabriel.bill.events;

import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.bill.valueobjects.BillName;
import lombok.Getter;

@Getter
public class BillRenamedEvent extends BillEvent {
    private final BillName name;

    public BillRenamedEvent(BillId billId, Long version, BillName name) {
        super(billId, version);
        this.name = name;
    }
}
