package dev.gabriel.validators;

import dev.gabriel.entities.Wallet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class WalletValidatorTest {
    @Test
    @DisplayName("Validating a wallet without constraints violations.")
    public void validateWalletTestCase1() {
        Wallet wallet = Wallet.create(1L, 0.0, "Wallet Name");

        WalletValidator walletValidator = new WalletValidator();
        List<String> errors = walletValidator.validate(wallet);

        Assertions.assertEquals(0, errors.size());
    }

    @Test
    @DisplayName("Validating a wallet with constraints violations.")
    public void validateWalletTestCase2() {
        Wallet wallet = Wallet.create(1L, 0.0, "");
        List<String> expectedErrors = Arrays.asList(new String[] {
                "Name must be between 1 and 255 characters."
        });
        WalletValidator walletValidator = new WalletValidator();

        List<String> errors = walletValidator.validate(wallet);

        Assertions.assertEquals(expectedErrors, errors);
    }
}
