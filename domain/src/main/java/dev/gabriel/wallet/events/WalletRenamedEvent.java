package dev.gabriel.wallet.events;

import lombok.Getter;

import java.util.UUID;

@Getter
public class WalletRenamedEvent extends WalletEvent {
    private final String name;

    public WalletRenamedEvent(UUID walletId, Long version, String name) {
        super(walletId, version);
        this.name = name;
    }
}
