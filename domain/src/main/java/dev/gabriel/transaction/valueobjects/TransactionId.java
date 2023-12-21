package dev.gabriel.transaction.valueobjects;

import dev.gabriel.shared.valueobjects.Identity;

public class TransactionId extends Identity {
    private TransactionId(String id) {
        super(id);
    }

    public static TransactionId create(String id) {
        return new TransactionId(id);
    }
}
