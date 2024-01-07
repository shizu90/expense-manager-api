package dev.gabriel.wallet.events;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class WalletBalanceUpdatedEvent extends WalletEvent {
    private final BigDecimal balance;

    public WalletBalanceUpdatedEvent(String walletId, Long version, BigDecimal balance) {
        super(walletId, version);
        this.balance = balance;
    }
}
