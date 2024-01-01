package dev.gabriel.wallet.events;

import dev.gabriel.wallet.valueobjects.WalletId;

public class WalletPrincipalUnmarkedEvent extends WalletEvent {
    public WalletPrincipalUnmarkedEvent(WalletId walletId) {
        super(walletId);
    }
}
