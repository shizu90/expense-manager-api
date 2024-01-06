package dev.gabriel.user.events;

import dev.gabriel.user.valueobjects.Password;
import dev.gabriel.user.valueobjects.UserId;
import lombok.Getter;

@Getter
public class UserPasswordChangedEvent extends UserEvent {
    private final Password password;

    public UserPasswordChangedEvent(UserId userId, Long version, Password password) {
        super(userId, version);
        this.password = password;
    }
}
