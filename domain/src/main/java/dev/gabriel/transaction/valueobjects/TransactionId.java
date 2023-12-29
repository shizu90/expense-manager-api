package dev.gabriel.transaction.valueobjects;

import dev.gabriel.shared.valueobjects.Identity;

public class TransactionId extends Identity {
    private TransactionId(String value) {
        super(value);
    }

    public static TransactionId create(String value) {
        return new TransactionId(value);
    }
}
