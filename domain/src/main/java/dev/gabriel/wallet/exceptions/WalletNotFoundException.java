package dev.gabriel.wallet.exceptions;

import dev.gabriel.shared.exceptions.NotFoundException;

public class WalletNotFoundException extends NotFoundException {
    public WalletNotFoundException(String id) {
        super("Wallet " + id + " was not found.");
    }
}
