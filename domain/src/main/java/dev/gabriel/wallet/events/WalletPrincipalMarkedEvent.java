package dev.gabriel.wallet.events;

import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.wallet.valueobjects.WalletId;

import java.time.Instant;

public class WalletPrincipalMarkedEvent extends DomainEvent {
    public WalletPrincipalMarkedEvent(WalletId walletId) {
        super(walletId, Instant.now());
    }
}
