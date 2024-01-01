package dev.gabriel.wallet.events;

import dev.gabriel.wallet.valueobjects.WalletId;

public class WalletDeletedEvent extends WalletEvent {
    public WalletDeletedEvent(WalletId walletId) {
        super(walletId);
    }
}
