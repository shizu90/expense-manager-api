package dev.gabriel.wallet.events;

import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.wallet.models.WalletType;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class WalletCreatedEvent extends WalletEvent {
    private final String name;
    private final String description;
    private final BigDecimal balance;
    private final CurrencyCode currencyCode;
    private final Boolean main;
    private final WalletType type;
    private final String userId;

    public WalletCreatedEvent(String walletId,
                              Long version,
                              String name,
                              String description,
                              BigDecimal balance,
                              CurrencyCode currencyCode,
                              Boolean main,
                              WalletType type,
                              String userId
    ) {
        super(walletId, version);
        this.name = name;
        this.description = description;
        this.balance = balance;
        this.currencyCode = currencyCode;
        this.main = main;
        this.type = type;
        this.userId = userId;
    }
}
