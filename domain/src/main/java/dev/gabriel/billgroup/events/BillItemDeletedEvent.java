package dev.gabriel.billgroup.events;

import dev.gabriel.billgroup.valueobjects.BillGroupId;
import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;

public class BillItemDeletedEvent extends DomainEvent {
    public BillItemDeletedEvent(BillGroupId billGroupId) {
        super(billGroupId, Instant.now());
    }
}
