package dev.gabriel.wallet.events;

import dev.gabriel.wallet.valueobjects.WalletId;

public class WalletRenamedEvent extends WalletEvent {
    public WalletRenamedEvent(WalletId walletId) {
        super(walletId);
    }
}
