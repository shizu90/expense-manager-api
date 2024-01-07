package dev.gabriel.user.events;

import lombok.Getter;

@Getter
public class UserPasswordChangedEvent extends UserEvent {
    private final String password;

    public UserPasswordChangedEvent(String userId, Long version, String password) {
        super(userId, version);
        this.password = password;
    }
}
