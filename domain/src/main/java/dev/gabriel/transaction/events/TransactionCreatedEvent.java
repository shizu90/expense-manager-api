package dev.gabriel.transaction.events;

import dev.gabriel.transaction.entities.Transaction;
import dev.gabriel.shared.events.DomainEvent;

public class TransactionCreatedEvent extends DomainEvent<Transaction> {
    public TransactionCreatedEvent(Transaction transaction) {
        super(transaction);
    }
}
