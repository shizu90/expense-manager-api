package dev.gabriel.user.validation;

import dev.gabriel.user.entities.User;
import dev.gabriel.user.validation.IUserValidator;
import dev.gabriel.user.validation.UserValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

public class UserValidatorTest {
    User populate() {
        return User.create(UUID.randomUUID().toString(), "", "", "");
    }

    @Test
    @DisplayName("Should validate name properly.")
    void validateNameTestCase() {
        User user = populate();
        IUserValidator userValidator = new UserValidator();
        String error = userValidator.validateName(user.getName());

        Assertions.assertNotNull(error);
    }

    @Test
    @DisplayName("Should validate email properly.")
    void validateEmailTestCase() {
        User user = populate();
        IUserValidator userValidator = new UserValidator();
        String error = userValidator.validateName(user.getEmail());

        Assertions.assertNotNull(error);
    }

    @Test
    @DisplayName("Should validate password properly.")
    void validatePasswordTestCase() {
        User user = populate();
        IUserValidator userValidator = new UserValidator();
        String error = userValidator.validateName(user.getPassword());

        Assertions.assertNotNull(error);
    }

    @Test
    @DisplayName("Should validate user properly.")
    void validateUserTestCase() {
        User user = populate();
        IUserValidator userValidator = new UserValidator();
        List<String> errors = userValidator.validate(user);

        Assertions.assertEquals(3, errors.size());
    }
}
