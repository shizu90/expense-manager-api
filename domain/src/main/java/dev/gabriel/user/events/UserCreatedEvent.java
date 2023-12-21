package dev.gabriel.user.events;

import dev.gabriel.user.entities.User;
import dev.gabriel.shared.events.DomainEvent;

public class UserCreatedEvent extends DomainEvent<User> {
    public UserCreatedEvent(User user) {
        super(user);
    }
}
