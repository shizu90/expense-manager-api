package dev.gabriel.wallet.events;

import lombok.Getter;

@Getter
public class WalletRenamedEvent extends WalletEvent {
    private final String name;

    public WalletRenamedEvent(String walletId, Long version, String name) {
        super(walletId, version);
        this.name = name;
    }
}
