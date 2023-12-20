package dev.gabriel.transaction.events;

import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.shared.valueobjects.Identity;

public class TransactionRemovedEvent extends DomainEvent {
    public TransactionRemovedEvent(Identity transactionId) {
        super(transactionId);
    }
}
