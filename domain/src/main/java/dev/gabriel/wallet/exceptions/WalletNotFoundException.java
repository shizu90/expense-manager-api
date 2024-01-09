package dev.gabriel.wallet.exceptions;

import dev.gabriel.shared.exceptions.NotFoundException;

import java.util.UUID;

public class WalletNotFoundException extends NotFoundException {
    public WalletNotFoundException(UUID id) {
        super("Wallet " + id.toString() + " was not found.");
    }
}
