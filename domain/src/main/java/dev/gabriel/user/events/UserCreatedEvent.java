package dev.gabriel.user.events;

import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.user.models.UserLanguage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserCreatedEvent extends UserEvent {
    private final String username;
    private final String email;
    private final String password;
    private final CurrencyCode defaultCurrencyCode;
    private final UserLanguage defaultLanguage;

    public UserCreatedEvent(UUID userId, Long version, String username, String email, String password, CurrencyCode defaultCurrencyCode, UserLanguage defaultLanguage) {
        super(userId, version);
        this.username = username;
        this.email = email;
        this.password = password;
        this.defaultCurrencyCode = defaultCurrencyCode;
        this.defaultLanguage = defaultLanguage;
    }
}
