package dev.gabriel.wallet.events;

import dev.gabriel.wallet.entities.Wallet;
import dev.gabriel.shared.events.DomainEvent;

public class WalletCreatedEvent extends DomainEvent<Wallet> {
    public WalletCreatedEvent(Wallet wallet) {
        super(wallet);
    }
}
