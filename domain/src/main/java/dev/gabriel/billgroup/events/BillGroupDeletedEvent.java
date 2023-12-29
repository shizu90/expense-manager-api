package dev.gabriel.billgroup.events;

import dev.gabriel.billgroup.valueobjects.BillGroupId;
import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;

public class BillGroupDeletedEvent extends DomainEvent {
    public BillGroupDeletedEvent(BillGroupId billGroupId) {
        super(billGroupId, Instant.now());
    }
}
