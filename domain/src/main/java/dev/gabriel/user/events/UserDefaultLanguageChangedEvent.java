package dev.gabriel.user.events;

import dev.gabriel.user.models.UserLanguage;
import dev.gabriel.user.valueobjects.UserId;
import lombok.Getter;

@Getter
public class UserDefaultLanguageChangedEvent extends UserEvent {
    private final UserLanguage language;

    public UserDefaultLanguageChangedEvent(UserId userId, Long version, UserLanguage language) {
        super(userId, version);
        this.language = language;
    }
}
