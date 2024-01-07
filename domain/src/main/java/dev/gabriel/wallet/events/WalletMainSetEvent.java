package dev.gabriel.wallet.events;

import dev.gabriel.shared.events.DomainEvent;
import lombok.Getter;

import java.time.Instant;

@Getter
public class WalletMainSetEvent extends DomainEvent {
    private final Boolean main;

    public WalletMainSetEvent(String walletId, Long version, Boolean main) {
        super(walletId, version, Instant.now());
        this.main = main;
    }
}
