package dev.gabriel.wallet.events;

import dev.gabriel.wallet.models.WalletType;
import lombok.Getter;

import java.util.UUID;

@Getter
public class WalletTypeChangedEvent extends WalletEvent {
    private final WalletType type;

    public WalletTypeChangedEvent(UUID walletId, Long version, WalletType type) {
        super(walletId, version);
        this.type = type;
    }
}
