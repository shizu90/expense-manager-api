package dev.gabriel.bill.events;

import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;

public class BillEvent extends DomainEvent {
    protected BillEvent(BillId billId) {
        super(billId, Instant.now());
    }
}
