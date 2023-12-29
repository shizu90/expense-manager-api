package dev.gabriel.billgroup.events;

import dev.gabriel.billgroup.valueobjects.BillGroupId;
import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;

public class BillGroupCommentEditedEvent extends DomainEvent {
    public BillGroupCommentEditedEvent(BillGroupId billGroupId) {
        super(billGroupId, Instant.now());
    }
}
