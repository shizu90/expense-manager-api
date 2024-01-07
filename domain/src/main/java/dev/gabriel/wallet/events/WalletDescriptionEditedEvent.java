package dev.gabriel.wallet.events;

import lombok.Getter;

@Getter
public class WalletDescriptionEditedEvent extends WalletEvent {
    private final String description;

    public WalletDescriptionEditedEvent(String walletId, Long version, String description) {
        super(walletId, version);
        this.description = description;
    }
}
