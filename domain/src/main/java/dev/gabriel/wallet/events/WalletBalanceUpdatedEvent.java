package dev.gabriel.wallet.events;

import dev.gabriel.wallet.valueobjects.WalletId;

public class WalletBalanceUpdatedEvent extends WalletEvent {
    public WalletBalanceUpdatedEvent(WalletId walletId) {
        super(walletId);
    }
}
