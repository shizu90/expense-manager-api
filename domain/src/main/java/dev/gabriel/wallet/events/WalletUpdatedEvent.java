package dev.gabriel.wallet.events;

import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.wallet.entities.Wallet;
import dev.gabriel.shared.events.DomainEvent;

public class WalletUpdatedEvent extends DomainEvent {
    public WalletUpdatedEvent(Identity walletId) {
        super(walletId);
    }
}
