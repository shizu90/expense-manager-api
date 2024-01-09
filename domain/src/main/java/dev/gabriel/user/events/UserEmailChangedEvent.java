package dev.gabriel.user.events;

import lombok.Getter;

import java.util.UUID;

@Getter
public class UserEmailChangedEvent extends UserEvent {
    private final String email;

    public UserEmailChangedEvent(UUID userId, Long version, String email) {
        super(userId, version);
        this.email = email;
    }
}
