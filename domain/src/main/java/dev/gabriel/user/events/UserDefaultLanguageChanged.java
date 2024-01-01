package dev.gabriel.user.events;

import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.user.valueobjects.UserId;

import java.time.Instant;

public class UserDefaultLanguageChanged extends DomainEvent {
    public UserDefaultLanguageChanged(UserId userId) {
        super(userId, Instant.now());
    }
}
