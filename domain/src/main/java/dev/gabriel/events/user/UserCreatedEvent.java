package dev.gabriel.events.user;

import dev.gabriel.entities.user.User;
import dev.gabriel.events.DomainEvent;

public class UserCreatedEvent extends DomainEvent<User> {
    public UserCreatedEvent() {

    }
}
