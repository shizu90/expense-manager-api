package dev.gabriel.user.events;

import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.user.models.UserLanguage;
import dev.gabriel.user.valueobjects.Email;
import dev.gabriel.user.valueobjects.Password;
import dev.gabriel.user.valueobjects.UserId;
import dev.gabriel.user.valueobjects.Username;
import lombok.Getter;

@Getter
public class UserCreatedEvent extends UserEvent {
    private final Username username;
    private final Email email;
    private final Password password;
    private final CurrencyCode defaultCurrencyCode;
    private final UserLanguage defaultLanguage;

    public UserCreatedEvent(UserId userId, Long version, Username username, Email email, Password password, CurrencyCode defaultCurrencyCode, UserLanguage defaultLanguage) {
        super(userId, version);
        this.username = username;
        this.email = email;
        this.password = password;
        this.defaultCurrencyCode = defaultCurrencyCode;
        this.defaultLanguage = defaultLanguage;
    }
}
