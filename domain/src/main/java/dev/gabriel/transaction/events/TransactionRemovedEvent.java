package dev.gabriel.transaction.events;

import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.transaction.entities.Transaction;

public class TransactionRemovedEvent extends DomainEvent<Transaction> {
    public TransactionRemovedEvent(Transaction transaction) {
        super(transaction);
    }
}
