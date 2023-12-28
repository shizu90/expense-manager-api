package dev.gabriel.wallet.exceptions;

import dev.gabriel.shared.exceptions.DataValidationException;

public class WalletValidationException extends DataValidationException {
    public WalletValidationException(String field, String message) {
        super("Wallet validation failed on " + field + ": " + message);
    }
}
