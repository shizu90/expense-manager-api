package dev.gabriel.user.events;

public class UserDeletedEvent extends UserEvent {

    public UserDeletedEvent(String userId, Long version) {
        super(userId, version);
    }
}
