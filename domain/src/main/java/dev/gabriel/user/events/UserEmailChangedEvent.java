package dev.gabriel.user.events;

import dev.gabriel.user.valueobjects.Email;
import dev.gabriel.user.valueobjects.UserId;
import lombok.Getter;

@Getter
public class UserEmailChangedEvent extends UserEvent {
    private final Email email;

    public UserEmailChangedEvent(UserId userId, Long version, Email email) {
        super(userId, version);
        this.email = email;
    }
}
