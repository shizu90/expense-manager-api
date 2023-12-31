package dev.gabriel.bill.events;

import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;

public class BillAmountChangedEvent extends DomainEvent {
    public BillAmountChangedEvent(BillId billId) {
        super(billId, Instant.now());
    }
}
