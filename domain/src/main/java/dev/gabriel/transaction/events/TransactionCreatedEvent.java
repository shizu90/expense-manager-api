package dev.gabriel.transaction.events;

import dev.gabriel.transaction.valueobjects.TransactionId;

public class TransactionCreatedEvent extends TransactionEvent {
    public TransactionCreatedEvent(TransactionId transactionId) {
        super(transactionId);
    }
}
