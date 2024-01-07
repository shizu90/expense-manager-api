package dev.gabriel.bill.events;

import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;

public class BillEvent extends DomainEvent {
    protected BillEvent(String billId, Long version) {
        super(billId, version, Instant.now());
    }
}
