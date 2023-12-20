package dev.gabriel.events.wallet;

import dev.gabriel.entities.wallet.Wallet;
import dev.gabriel.events.DomainEvent;

public class WalletCreatedEvent extends DomainEvent<Wallet> {
    public WalletCreatedEvent() {

    }
}
