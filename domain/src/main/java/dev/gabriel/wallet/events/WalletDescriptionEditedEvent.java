package dev.gabriel.wallet.events;

import dev.gabriel.wallet.valueobjects.WalletId;

public class WalletDescriptionEditedEvent extends WalletEvent {
    public WalletDescriptionEditedEvent(WalletId walletId) {
        super(walletId);
    }
}
