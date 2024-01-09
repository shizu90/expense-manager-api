package dev.gabriel.user.events;

import lombok.Getter;

import java.util.UUID;

@Getter
public class UserPasswordChangedEvent extends UserEvent {
    private final String password;

    public UserPasswordChangedEvent(UUID userId, Long version, String password) {
        super(userId, version);
        this.password = password;
    }
}
