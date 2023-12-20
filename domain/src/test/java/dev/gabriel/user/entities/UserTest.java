package dev.gabriel.user.entities;

import dev.gabriel.shared.entities.AggregateRoot;
import dev.gabriel.shared.entities.Entity;
import dev.gabriel.user.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UserTest {
    @Test
    @DisplayName("Should create user successfully.")
    void createUserTestCase() {
        String uid = UUID.randomUUID().toString();
        User user = User.create(uid, "Test", "test@gmail.com", "test123");

        Assertions.assertInstanceOf(AggregateRoot.class, user);
        Assertions.assertInstanceOf(Entity.class, user.getConfiguration());
        Assertions.assertEquals(uid, user.getIdentity());
        Assertions.assertEquals(uid, user.getConfiguration().getIdentity());
    }
}
