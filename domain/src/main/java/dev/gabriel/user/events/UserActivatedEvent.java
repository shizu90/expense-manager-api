package dev.gabriel.user.events;

import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.user.entities.User;

public class UserActivatedEvent extends DomainEvent<User> {
    public UserActivatedEvent(User user) {
        super(user);
    }
}
