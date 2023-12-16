package dev.gabriel.validators;

import dev.gabriel.entities.User;
import dev.gabriel.entities.Wallet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class UserValidatorTest {
    @Test
    @DisplayName("Validating a user without constraints violations.")
    public void validateUserTestCase1() {
        User user = User.create(1L, "User", "user@gmail.com", "user123");
        user.addWallet(Wallet.create(1L, 0.0, "Wallet"));
        UserValidator userValidator = new UserValidator();
        List<String> errors = userValidator.validate(user);

        Assertions.assertEquals(0, errors.size());
    }

    @Test
    @DisplayName("Validating a user with constraints violations.")
    public void validateUserTestCase2() {
        User user = User.create(1L, "U", "gmail.com", "123");
        List<String> expectedErrors = Arrays.asList(new String[] {
                "Name must be between 3 and 128 characters.",
                "Email is not a valid address.",
                "Password must be between 6 and 24 characters.",
                "User must have a wallet and not more than 4."
        });
        UserValidator userValidator = new UserValidator();
        List<String> errors = userValidator.validate(user);

        Assertions.assertEquals(expectedErrors, errors);
    }
}
