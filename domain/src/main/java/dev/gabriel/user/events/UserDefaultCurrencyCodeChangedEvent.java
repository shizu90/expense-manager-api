package dev.gabriel.user.events;

import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.user.valueobjects.UserId;
import lombok.Getter;

@Getter
public class UserDefaultCurrencyCodeChangedEvent extends UserEvent {
    private final CurrencyCode currencyCode;

    public UserDefaultCurrencyCodeChangedEvent(UserId userId, Long version, CurrencyCode currencyCode) {
        super(userId, version);
        this.currencyCode = currencyCode;
    }
}
