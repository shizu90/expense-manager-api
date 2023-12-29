package dev.gabriel.transaction.events;

import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.transaction.valueobjects.TransactionId;

import java.time.Instant;

public class TransactionDeletedEvent extends DomainEvent {
    public TransactionDeletedEvent(TransactionId transactionId) {
        super(transactionId, Instant.now());
    }
}
