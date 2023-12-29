package dev.gabriel.bill.events;

import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;

public class BillCreatedEvent extends DomainEvent {
    public BillCreatedEvent(BillId billId) {
        super(billId, Instant.now());
    }
}
