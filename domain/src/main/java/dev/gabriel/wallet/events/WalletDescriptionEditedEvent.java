package dev.gabriel.wallet.events;

import lombok.Getter;

import java.util.UUID;

@Getter
public class WalletDescriptionEditedEvent extends WalletEvent {
    private final String description;

    public WalletDescriptionEditedEvent(UUID walletId, Long version, String description) {
        super(walletId, version);
        this.description = description;
    }
}
