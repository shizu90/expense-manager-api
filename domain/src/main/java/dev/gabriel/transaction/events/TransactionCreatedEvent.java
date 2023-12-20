package dev.gabriel.transaction.events;

import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.transaction.entities.Transaction;
import dev.gabriel.shared.events.DomainEvent;

public class TransactionCreatedEvent extends DomainEvent {
    public TransactionCreatedEvent(Identity transactionId) {
        super(transactionId);
    }
}
