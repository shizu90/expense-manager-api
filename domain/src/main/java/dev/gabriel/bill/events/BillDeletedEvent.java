package dev.gabriel.bill.events;

import java.util.UUID;

public class BillDeletedEvent extends BillEvent {
    public BillDeletedEvent(UUID billId, Long version) {
        super(billId, version);
    }
}
