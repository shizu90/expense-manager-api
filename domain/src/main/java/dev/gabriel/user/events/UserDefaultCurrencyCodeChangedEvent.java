package dev.gabriel.user.events;

import dev.gabriel.shared.valueobjects.CurrencyCode;
import lombok.Getter;

@Getter
public class UserDefaultCurrencyCodeChangedEvent extends UserEvent {
    private final CurrencyCode currencyCode;

    public UserDefaultCurrencyCodeChangedEvent(String userId, Long version, CurrencyCode currencyCode) {
        super(userId, version);
        this.currencyCode = currencyCode;
    }
}
