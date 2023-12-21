package dev.gabriel.user.entities;

import dev.gabriel.shared.entities.AggregateRoot;
import dev.gabriel.shared.entities.Entity;
import dev.gabriel.user.entities.User;
import dev.gabriel.user.events.UserCreatedEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UserTest {

    private User populate() {
        return User.create(UUID.randomUUID().toString(), "Test", "test@gmail.com", "test123");
    }

    @Test
    @DisplayName("Should create user successfully.")
    void createUserTestCase() {
        User user = populate();

        Assertions.assertInstanceOf(AggregateRoot.class, user);
        Assertions.assertInstanceOf(Entity.class, user.getConfiguration());
        Assertions.assertInstanceOf(UserCreatedEvent.class, user.getEvents().get(0));
    }
}
