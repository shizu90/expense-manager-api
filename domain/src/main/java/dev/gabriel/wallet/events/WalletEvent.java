package dev.gabriel.wallet.events;

import dev.gabriel.shared.events.DomainEvent;

import java.time.Instant;
import java.util.UUID;

public class WalletEvent extends DomainEvent {
    protected WalletEvent(UUID walletId, Long version) {
        super(walletId, version, Instant.now());
    }
}
