package dev.gabriel.user.models;

import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.user.events.*;
import dev.gabriel.user.exceptions.UserAlreadyDeletedException;
import dev.gabriel.user.exceptions.UserValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class UserTests {
    User populate() {
        return User.create(
                UUID.randomUUID().toString(),
                "email@gmail.com",
                "Username",
                "Password123",
                CurrencyCode.BRL,
                UserLanguage.PT_BR
        );
    }

    @Test
    @DisplayName("Create user")
    void createUser() {
        User user = populate();

        Assertions.assertInstanceOf(UserCreatedEvent.class, user.getEvents().get(0));
        Assertions.assertEquals("Username", user.getName().getValue());
    }

    @Test
    @DisplayName("Create user from event stream")
    void createUserFromEventStream() {
        User user = populate();
        List<DomainEvent> events = Arrays.asList(new DomainEvent[] {
                new UserCreatedEvent(
                        user.getId().getValue(),
                        user.getBaseVersion(),
                        user.getName().getValue(),
                        user.getEmail().getValue(),
                        user.getPassword().getValue(),
                        user.getConfiguration().getDefaultCurrencyCode(),
                        user.getConfiguration().getDefaultUserLanguage()
                )
        });
        User userFromEventStream = User.create(user.getId().getValue(), events);

        Assertions.assertEquals(user.getId(), userFromEventStream.getId());
    }

    @Test
    @DisplayName("Rename user")
    void renameUser() {
        User user = populate();
        user.rename("NewUsername");

        Assertions.assertInstanceOf(UserRenamedEvent.class, user.getEvents().get(1));
        Assertions.assertEquals("NewUsername", user.getName().getValue());
    }

    @Test
    @DisplayName("Rename user should throw UserValidationException")
    void renameUserShouldThrowUserValidationException() {
        User user = populate();

        Assertions.assertThrows(UserValidationException.class, () -> {
            user.rename(null);
        });
        Assertions.assertEquals(1, user.getEvents().size());
    }

    @Test
    @DisplayName("Change email")
    void changeEmail() {
        User user = populate();
        user.changeEmail("newemail@gmail.com");

        Assertions.assertInstanceOf(UserEmailChangedEvent.class, user.getEvents().get(1));
        Assertions.assertEquals("newemail@gmail.com", user.getEmail().getValue());
    }

    @Test
    @DisplayName("Change email should throw UserValidationException")
    void changeEmailShouldThrowUserValidationException() {
        User user = populate();

        Assertions.assertThrows(UserValidationException.class, () -> {
            user.changeEmail(null);
        });
        Assertions.assertEquals(1, user.getEvents().size());
    }

    @Test
    @DisplayName("Change password")
    void changePassword() {
        User user = populate();
        user.changePassword("NewPassword123");

        Assertions.assertInstanceOf(UserPasswordChangedEvent.class, user.getEvents().get(1));
        Assertions.assertEquals("NewPassword123", user.getPassword().getValue());
    }

    @Test
    @DisplayName("Change password should throw UserValidationException")
    void changePasswordShouldThrowUserValidationException() {
        User user = populate();

        Assertions.assertThrows(UserValidationException.class, () -> {
            user.changePassword(null);
        });
        Assertions.assertEquals(1, user.getEvents().size());
    }

    @Test
    @DisplayName("Change default currency code")
    void changeDefaultCurrencyCode() {
        User user = populate();
        user.changeDefaultCurrencyCode(CurrencyCode.EUR);

        Assertions.assertInstanceOf(UserDefaultCurrencyCodeChangedEvent.class, user.getEvents().get(1));
        Assertions.assertEquals(CurrencyCode.EUR, user.getConfiguration().getDefaultCurrencyCode());
    }

    @Test
    @DisplayName("Change default language")
    void changeDefaultLanguage() {
        User user = populate();
        user.changeDefaultUserLanguage(UserLanguage.EN_US);

        Assertions.assertInstanceOf(UserDefaultLanguageChangedEvent.class, user.getEvents().get(1));
        Assertions.assertEquals(UserLanguage.EN_US, user.getConfiguration().getDefaultUserLanguage());
    }

    @Test
    @DisplayName("Delete user")
    void deleteUser() {
        User user = populate();
        user.delete();

        Assertions.assertInstanceOf(UserDeletedEvent.class, user.getEvents().get(1));
        Assertions.assertTrue(user.getIsDeleted());
    }

    @Test
    @DisplayName("Delete user should throw UserAlreadyDeletedException")
    void deleteUserShouldThrowUserAlreadyDeletedException() {
        User user = populate();
        user.delete();

        Assertions.assertInstanceOf(UserDeletedEvent.class, user.getEvents().get(1));
        Assertions.assertThrows(UserAlreadyDeletedException.class, user::delete);
        Assertions.assertEquals(2, user.getEvents().size());
    }
}
