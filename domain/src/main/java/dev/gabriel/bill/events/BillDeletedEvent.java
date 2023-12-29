package dev.gabriel.bill.events;

import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;

public class BillDeletedEvent extends DomainEvent {
    public BillDeletedEvent(BillId billId) {
        super(billId, Instant.now());
    }
}
