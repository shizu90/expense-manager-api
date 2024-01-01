package dev.gabriel.wallet.events;

import dev.gabriel.wallet.valueobjects.WalletId;

public class WalletCurrencyCodeChangedEvent extends WalletEvent {
    public WalletCurrencyCodeChangedEvent(WalletId walletId) {
        super(walletId);
    }
}
