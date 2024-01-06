package dev.gabriel.wallet.events;

import dev.gabriel.shared.valueobjects.Currency;
import dev.gabriel.wallet.valueobjects.WalletId;
import lombok.Getter;

@Getter
public class WalletBalanceUpdatedEvent extends WalletEvent {
    private final Currency balance;

    public WalletBalanceUpdatedEvent(WalletId walletId, Long version, Currency balance) {
        super(walletId, version);
        this.balance = balance;
    }
}
