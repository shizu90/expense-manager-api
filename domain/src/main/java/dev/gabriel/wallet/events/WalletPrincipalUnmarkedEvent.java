package dev.gabriel.wallet.events;

import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.wallet.valueobjects.WalletId;

import java.time.Instant;

public class WalletPrincipalUnmarkedEvent extends DomainEvent {
    public WalletPrincipalUnmarkedEvent(WalletId walletId) {
        super(walletId, Instant.now());
    }
}
