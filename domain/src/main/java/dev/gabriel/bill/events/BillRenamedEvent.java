package dev.gabriel.bill.events;

import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;

public class BillRenamedEvent extends DomainEvent {
    public BillRenamedEvent(BillId billId) {
        super(billId, Instant.now());
    }
}
