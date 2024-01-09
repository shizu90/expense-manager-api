package dev.gabriel.wallet.events;

import java.util.UUID;

public class WalletDeletedEvent extends WalletEvent {
    public WalletDeletedEvent(UUID walletId, Long version) {
        super(walletId, version);
    }
}
