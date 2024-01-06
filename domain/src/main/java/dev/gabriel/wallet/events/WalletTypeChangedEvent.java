package dev.gabriel.wallet.events;

import dev.gabriel.wallet.models.WalletType;
import dev.gabriel.wallet.valueobjects.WalletId;
import lombok.Getter;

@Getter
public class WalletTypeChangedEvent extends WalletEvent {
    private final WalletType type;

    public WalletTypeChangedEvent(WalletId walletId, Long version, WalletType type) {
        super(walletId, version);
        this.type = type;
    }
}
