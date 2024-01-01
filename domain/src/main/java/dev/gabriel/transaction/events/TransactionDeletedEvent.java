package dev.gabriel.transaction.events;

import dev.gabriel.transaction.valueobjects.TransactionId;

public class TransactionDeletedEvent extends TransactionEvent {
    public TransactionDeletedEvent(TransactionId transactionId) {
        super(transactionId);
    }
}
