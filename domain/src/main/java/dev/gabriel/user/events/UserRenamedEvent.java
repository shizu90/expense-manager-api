package dev.gabriel.user.events;

import dev.gabriel.user.valueobjects.UserId;
import dev.gabriel.user.valueobjects.Username;
import lombok.Getter;

@Getter
public class UserRenamedEvent extends UserEvent {
    private final Username name;

    public UserRenamedEvent(UserId userId, Long version, Username name) {
        super(userId, version);
        this.name = name;
    }
}
