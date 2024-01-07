package dev.gabriel.wallet.events;

import dev.gabriel.shared.valueobjects.CurrencyCode;
import lombok.Getter;

@Getter
public class WalletCurrencyCodeChangedEvent extends WalletEvent {
    private final CurrencyCode currencyCode;

    public WalletCurrencyCodeChangedEvent(String walletId, Long version, CurrencyCode currencyCode) {
        super(walletId, version);
        this.currencyCode = currencyCode;
    }
}
