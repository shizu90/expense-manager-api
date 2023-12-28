package dev.gabriel.wallet.valueobjects;

import dev.gabriel.shared.valueobjects.Identity;

public class WalletId extends Identity {
    private WalletId(String value) {
        super(value);
    }

    public static WalletId create(String value) {
        return new WalletId(value);
    }
}
