package dev.gabriel.wallet.valueobjects;

import dev.gabriel.shared.valueobjects.Identity;

import java.util.UUID;

public class WalletId extends Identity {
    private WalletId(UUID value) {
        super(value);
    }

    public static WalletId create(UUID value) {
        return new WalletId(value);
    }
}
