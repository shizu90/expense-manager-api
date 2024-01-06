package dev.gabriel.wallet.events;

import dev.gabriel.wallet.valueobjects.WalletDescription;
import dev.gabriel.wallet.valueobjects.WalletId;
import lombok.Getter;

@Getter
public class WalletDescriptionEditedEvent extends WalletEvent {
    private final WalletDescription description;

    public WalletDescriptionEditedEvent(WalletId walletId, Long version, WalletDescription description) {
        super(walletId, version);
        this.description = description;
    }
}
