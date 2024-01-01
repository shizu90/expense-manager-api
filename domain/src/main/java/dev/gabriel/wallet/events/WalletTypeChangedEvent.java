package dev.gabriel.wallet.events;

import dev.gabriel.wallet.valueobjects.WalletId;

public class WalletTypeChangedEvent extends WalletEvent {
    public WalletTypeChangedEvent(WalletId walletId) {
        super(walletId);
    }
}
