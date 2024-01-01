package dev.gabriel.wallet.models;

import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.shared.valueobjects.Currency;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.user.valueobjects.UserId;
import dev.gabriel.wallet.events.*;
import dev.gabriel.wallet.exceptions.WalletValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

public class WalletTests {
    Wallet populate() {
        return Wallet.create(
                UUID.randomUUID().toString(),
                "Name",
                "Description",
                BigDecimal.valueOf(500.0),
                CurrencyCode.BRL,
                true,
                WalletType.DEBIT_CARD,
                UserId.create(UUID.randomUUID().toString()));
    }

    @Test
    @DisplayName("Create wallet test case: success")
    void createWalletTestCaseSuccess() {
        Wallet wallet = populate();

        Assertions.assertInstanceOf(AggregateRoot.class, wallet);
        Assertions.assertInstanceOf(WalletCreatedEvent.class, wallet.getEvents().get(0));
    }

    @Test
    @DisplayName("Rename wallet test case: success")
    void renameWalletTestCaseSuccess() {
        Wallet wallet = populate();
        wallet.rename("CoolName");

        Assertions.assertInstanceOf(WalletRenamedEvent.class, wallet.getEvents().get(1));
    }

    @Test
    @DisplayName("Rename wallet test case: failed")
    void renameWalletTestCaseFailed() {
        Wallet wallet = populate();

        Assertions.assertThrows(WalletValidationException.class, () -> {
            wallet.rename(null);
        });
    }

    @Test
    @DisplayName("Edit wallet description test case: success")
    void editWalletDescriptionTestCaseSuccess() {
        Wallet wallet = populate();
        wallet.editDescription("CoolDescription");

        Assertions.assertInstanceOf(WalletDescriptionEditedEvent.class, wallet.getEvents().get(1));
    }

    @Test
    @DisplayName("Edit wallet description test case: failed")
    void editWalletDescriptionTestCaseFailed() {
        Wallet wallet = populate();

        Assertions.assertThrows(WalletValidationException.class, () -> {
            wallet.editDescription(null);
        });
    }

    @Test
    @DisplayName("Change wallet currency code test case: success")
    void changeWalletCurrencyCodeTestCaseSuccess() {
        Wallet wallet = populate();
        wallet.changeCurrencyCode(CurrencyCode.USD);

        Assertions.assertInstanceOf(WalletCurrencyCodeChangedEvent.class, wallet.getEvents().get(1));
    }

    @Test
    @DisplayName("Update wallet balance test case: success")
    void updateWalletBalanceTestCaseSuccess() {
        Wallet wallet = populate();
        wallet.updateBalance(BigDecimal.valueOf(700.0));

        Assertions.assertInstanceOf(WalletBalanceUpdatedEvent.class, wallet.getEvents().get(1));
    }

    @Test
    @DisplayName("Change wallet type test case: success")
    void changeWalletTypeTestCaseSuccess() {
        Wallet wallet = populate();
        wallet.changeType(WalletType.CASH);

        Assertions.assertInstanceOf(WalletTypeChangedEvent.class, wallet.getEvents().get(1));
    }

    @Test
    @DisplayName("Mark wallet as principal test case: success")
    void markWalletAsPrincipalTestCaseSuccess() {
        Wallet wallet = populate();
        wallet.unmarkPrincipal();
        wallet.markPrincipal();

        Assertions.assertInstanceOf(WalletPrincipalUnmarkedEvent.class, wallet.getEvents().get(1));
        Assertions.assertInstanceOf(WalletPrincipalMarkedEvent.class, wallet.getEvents().get(2));
    }

    @Test
    @DisplayName("Unmark wallet as principal test case: success")
    void unmarkWalletAsPrincipalTestCaseSuccess() {
        Wallet wallet = populate();
        wallet.unmarkPrincipal();

        Assertions.assertInstanceOf(WalletPrincipalUnmarkedEvent.class, wallet.getEvents().get(1));
    }

    @Test
    @DisplayName("Delete wallet test case: success")
    void deleteWalletTestCaseSuccess() {
        Wallet wallet = populate();
        wallet.delete();

        Assertions.assertInstanceOf(WalletDeletedEvent.class, wallet.getEvents().get(1));
    }
}