package dev.gabriel.bill.events;

import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public class BillEvent extends DomainEvent {
    protected BillEvent(UUID billId, Long version) {
        super(billId, version, Instant.now());
    }
}
