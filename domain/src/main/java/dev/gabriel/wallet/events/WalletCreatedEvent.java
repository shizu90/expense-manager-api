package dev.gabriel.wallet.events;

import dev.gabriel.wallet.valueobjects.WalletId;

public class WalletCreatedEvent extends WalletEvent {
    public WalletCreatedEvent(WalletId walletId) {
        super(walletId);
    }
}
