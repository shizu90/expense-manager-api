package dev.gabriel.wallet.events;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class WalletBalanceUpdatedEvent extends WalletEvent {
    private final BigDecimal balance;

    public WalletBalanceUpdatedEvent(UUID walletId, Long version, BigDecimal balance) {
        super(walletId, version);
        this.balance = balance;
    }
}
