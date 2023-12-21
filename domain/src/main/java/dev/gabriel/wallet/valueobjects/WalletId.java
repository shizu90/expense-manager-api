package dev.gabriel.wallet.valueobjects;

import dev.gabriel.shared.valueobjects.Identity;

public class WalletId extends Identity {
    private WalletId(String id) {
        super(id);
    }

    public static WalletId create(String id) {
        return new WalletId(id);
    }
}
