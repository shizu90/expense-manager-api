package dev.gabriel.wallet.events;

import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.wallet.valueobjects.WalletId;

import java.time.Instant;

public class WalletEvent extends DomainEvent {
    protected WalletEvent(WalletId walletId) {
        super(walletId, Instant.now());
    }
}
