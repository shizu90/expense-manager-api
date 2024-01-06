package dev.gabriel.wallet.events;

import dev.gabriel.wallet.valueobjects.WalletId;
import dev.gabriel.wallet.valueobjects.WalletName;
import lombok.Getter;

@Getter
public class WalletRenamedEvent extends WalletEvent {
    private final WalletName name;

    public WalletRenamedEvent(WalletId walletId, Long version, WalletName name) {
        super(walletId, version);
        this.name = name;
    }
}
