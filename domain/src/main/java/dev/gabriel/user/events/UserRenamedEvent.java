package dev.gabriel.user.events;

import lombok.Getter;

@Getter
public class UserRenamedEvent extends UserEvent {
    private final String name;

    public UserRenamedEvent(String userId, Long version, String name) {
        super(userId, version);
        this.name = name;
    }
}
