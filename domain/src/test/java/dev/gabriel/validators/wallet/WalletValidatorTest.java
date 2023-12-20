package dev.gabriel.validators.wallet;

import dev.gabriel.entities.wallet.Wallet;
import dev.gabriel.enums.CurrencyType;
import dev.gabriel.valueobjects.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class WalletValidatorTest {
    Wallet populate() {
        return Wallet.create(UUID.randomUUID().toString(), Money.create(BigDecimal.valueOf(0), CurrencyType.BRL), "", "", null);
    }

    @Test
    @DisplayName("Should validate name properly.")
    void validateNameTestCase() {
        Wallet wallet = populate();
        IWalletValidator walletValidator = new WalletValidator();
        String error = walletValidator.validateName(wallet.getName());

        Assertions.assertNotNull(error);
    }

    @Test
    @DisplayName("Should validate comment properly.")
    void validateCommentTestCase() {
        Wallet wallet = populate();
        IWalletValidator walletValidator = new WalletValidator();
        String error = walletValidator.validateComment(wallet.getComment());

        Assertions.assertNotNull(error);
    }

    @Test
    @DisplayName("Should validate user id properly.")
    void validateUserIdTestCase() {
        Wallet wallet = populate();
        IWalletValidator walletValidator = new WalletValidator();
        String error = walletValidator.validateUserId(wallet.getUserId());

        Assertions.assertNotNull(error);
    }

    @Test
    @DisplayName("Should validate initial balance properly.")
    void validateInitialBalanceTestCase() {
        Wallet wallet = populate();
        IWalletValidator walletValidator = new WalletValidator();
        String error = walletValidator.validateInitialBalance(wallet.getInitialBalance());

        Assertions.assertNull(error);
    }

    @Test
    @DisplayName("Should validate last balance update properly.")
    void validateLastBalanceUpdate() {
        Wallet wallet = populate();
        IWalletValidator walletValidator = new WalletValidator();
        String error = walletValidator.validateLastBalanceUpdate(wallet.getLastBalanceUpdate());

        Assertions.assertNull(error);
    }

    @Test
    @DisplayName("Should validate wallet properly.")
    void validateWalletTestCase() {
        Wallet wallet = populate();
        IWalletValidator walletValidator = new WalletValidator();
        List<String> errors = walletValidator.validate(wallet);

        Assertions.assertEquals(3, errors.size());
    }
}
