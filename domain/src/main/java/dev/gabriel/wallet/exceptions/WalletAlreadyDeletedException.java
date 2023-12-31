package dev.gabriel.wallet.exceptions;

import dev.gabriel.shared.exceptions.AlreadyDeletedException;

public class WalletAlreadyDeletedException extends AlreadyDeletedException {
    public WalletAlreadyDeletedException() {
        super("Wallet is already deleted.");
    }
}
