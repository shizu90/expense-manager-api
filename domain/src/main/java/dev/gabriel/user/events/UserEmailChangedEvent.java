package dev.gabriel.user.events;

import lombok.Getter;

@Getter
public class UserEmailChangedEvent extends UserEvent {
    private final String email;

    public UserEmailChangedEvent(String userId, Long version, String email) {
        super(userId, version);
        this.email = email;
    }
}
