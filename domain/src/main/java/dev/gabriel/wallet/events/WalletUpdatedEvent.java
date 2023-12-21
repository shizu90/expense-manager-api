package dev.gabriel.wallet.events;

import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.wallet.entities.Wallet;

public class WalletUpdatedEvent extends DomainEvent<Wallet> {
    public WalletUpdatedEvent(Wallet wallet) {
        super(wallet);
    }
}
