package dev.gabriel.user.events;

import dev.gabriel.user.models.UserLanguage;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserDefaultLanguageChangedEvent extends UserEvent {
    private final UserLanguage language;

    public UserDefaultLanguageChangedEvent(UUID userId, Long version, UserLanguage language) {
        super(userId, version);
        this.language = language;
    }
}
