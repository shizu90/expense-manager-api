package dev.gabriel.wallet.events;

import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;

public class WalletEvent extends DomainEvent {
    protected WalletEvent(String walletId, Long version) {
        super(walletId, version, Instant.now());
    }
}
