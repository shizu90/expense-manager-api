package dev.gabriel.billgroup.events;

import dev.gabriel.billgroup.valueobjects.BillGroupId;
import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;

public class BillGroupRenamedEvent extends DomainEvent {
    public BillGroupRenamedEvent(BillGroupId billGroupId) {
        super(billGroupId, Instant.now());
    }
}
