package dev.gabriel.user.events;

import lombok.Getter;

import java.util.UUID;

@Getter
public class UserRenamedEvent extends UserEvent {
    private final String name;

    public UserRenamedEvent(UUID userId, Long version, String name) {
        super(userId, version);
        this.name = name;
    }
}
