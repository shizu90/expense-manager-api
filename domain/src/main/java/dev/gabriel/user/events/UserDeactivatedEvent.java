package dev.gabriel.user.events;

import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.user.entities.User;

public class UserDeactivatedEvent extends DomainEvent<User> {
    public UserDeactivatedEvent(User user) {
        super(user);
    }
}
