package dev.gabriel.user.models;

import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.user.events.*;
import dev.gabriel.user.exceptions.UserAlreadyActivatedException;
import dev.gabriel.user.exceptions.UserAlreadyDeactivatedException;
import dev.gabriel.user.exceptions.UserValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UserTests {
    User populate() {
        return User.create(
                UUID.randomUUID().toString(),
                "user@gmail.com",
                "Username",
                "password123",
                CurrencyCode.BRL,
                UserLanguage.PT_BR
        );
    }

    @Test
    @DisplayName("Create user test case: success")
    void createUserTestCaseSuccess() {
        User user = populate();

        Assertions.assertInstanceOf(AggregateRoot.class, user);
        Assertions.assertInstanceOf(UserCreatedEvent.class, user.getEvents().get(0));
    }

    @Test
    @DisplayName("Rename user test case: success")
    void renameUserTestCaseSuccess() {
        User user = populate();
        user.rename("CoolUsername");

        Assertions.assertInstanceOf(UserRenamedEvent.class, user.getEvents().get(1));
    }

    @Test
    @DisplayName("Rename user test case: failed")
    void renameUserTestCaseFailed() {
        User user = populate();

        Assertions.assertThrows(UserValidationException.class, () -> {
            user.rename(null);
        });
    }

    @Test
    @DisplayName("Change user email test case: success")
    void changeUserEmailTestCaseSuccess() {
        User user = populate();
        user.changeEmail("coolemail@gmail.com");

        Assertions.assertInstanceOf(UserEmailChangedEvent.class, user.getEvents().get(1));
    }

    @Test
    @DisplayName("Change user email test case: failed")
    void changeUserEmailTestCaseFailed() {
        User user = populate();

        Assertions.assertThrows(UserValidationException.class, () -> {
            user.changeEmail(null);
        });
    }

    @Test
    @DisplayName("Change user password test case: success")
    void changeUserPasswordTestCaseSuccess() {
        User user = populate();
        user.changePassword("coolpassword123");

        Assertions.assertInstanceOf(UserPasswordChangedEvent.class, user.getEvents().get(1));
    }

    @Test
    @DisplayName("Change user password test case: failed")
    void changeUserPasswordTestCaseFailed() {
        User user = populate();

        Assertions.assertThrows(UserValidationException.class, () -> {
            user.changePassword(null);
        });
    }

    @Test
    @DisplayName("Change user default currency code test case: success")
    void changeUserDefaultCurrencyCodeTestCaseSuccess() {
        User user = populate();
        user.changeDefaultCurrencyCode(CurrencyCode.EUR);

        Assertions.assertInstanceOf(UserDefaultCurrencyCodeChanged.class, user.getEvents().get(1));
    }

    @Test
    @DisplayName("Change user default language test case: success")
    void changeUserDefaultLanguageTestCaseSuccess() {
        User user = populate();
        user.changeDefaultUserLanguage(UserLanguage.EN_US);

        Assertions.assertInstanceOf(UserDefaultLanguageChanged.class, user.getEvents().get(1));
    }

    @Test
    @DisplayName("Activate user test case: success")
    void activateUserTestCaseSuccess() {
        User user = populate();
        user.deactivate();
        user.activate();

        Assertions.assertInstanceOf(UserDeactivatedEvent.class, user.getEvents().get(1));
        Assertions.assertInstanceOf(UserActivatedEvent.class, user.getEvents().get(2));
    }

    @Test
    @DisplayName("Activate user test case: failed")
    void activateUserTestCaseFailed() {
        User user = populate();
        user.deactivate();
        user.activate();

        Assertions.assertThrows(UserAlreadyActivatedException.class, user::activate);
    }

    @Test
    @DisplayName("Deactivate user test case: success")
    void deactivateUserTestCaseSuccess() {
        User user = populate();
        user.deactivate();

        Assertions.assertInstanceOf(UserDeactivatedEvent.class, user.getEvents().get(1));
    }

    @Test
    @DisplayName("Deactivate user test case: failed")
    void deactivateUserTestCaseFailed() {
        User user = populate();
        user.deactivate();

        Assertions.assertThrows(UserAlreadyDeactivatedException.class, user::deactivate);
    }
}
