package dev.gabriel.wallet.models;

import dev.gabriel.shared.events.DomainEvent;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.user.valueobjects.UserId;
import dev.gabriel.wallet.events.*;
import dev.gabriel.wallet.exceptions.WalletAlreadyDeletedException;
import dev.gabriel.wallet.exceptions.WalletValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class WalletTests {
    Wallet populate() {
        return Wallet.create(
                UUID.randomUUID().toString(),
                "Name",
                "Description",
                BigDecimal.valueOf(1200.0),
                CurrencyCode.BRL,
                true,
                WalletType.CREDIT_CARD,
                UserId.create(UUID.randomUUID().toString())
        );
    }

    @Test
    @DisplayName("Create wallet")
    void createWallet() {
        Wallet wallet = populate();

        Assertions.assertInstanceOf(WalletCreatedEvent.class, wallet.getEvents().get(0));
        Assertions.assertEquals("Name", wallet.getName().getValue());
    }

    @Test
    @DisplayName("Create wallet from event stream")
    void createWalletFromEventStream() {
        Wallet wallet = populate();
        List<DomainEvent> events = Arrays.asList(new DomainEvent[] {
                new WalletCreatedEvent(
                        wallet.getId().getValue(),
                        wallet.getBaseVersion(),
                        wallet.getName().getValue(),
                        wallet.getDescription().getValue(),
                        wallet.getBalance().getValue(),
                        wallet.getBalance().getCurrencyCode(),
                        wallet.getMain(),
                        wallet.getType(),
                        wallet.getUserId().getValue()
                )
        });
        Wallet walletFromEventStream = Wallet.create(wallet.getId().getValue(), events);

        Assertions.assertEquals(wallet.getId(), walletFromEventStream.getId());
    }

    @Test
    @DisplayName("Rename wallet")
    void renameWallet() {
        Wallet wallet = populate();
        wallet.rename("NewName");

        Assertions.assertInstanceOf(WalletRenamedEvent.class, wallet.getEvents().get(1));
        Assertions.assertEquals("NewName", wallet.getName().getValue());
    }

    @Test
    @DisplayName("Rename wallet should throw WalletValidationException")
    void renameWalletShouldThrowWalletValidationException() {
        Wallet wallet = populate();

        Assertions.assertThrows(WalletValidationException.class, () -> {
            wallet.rename(null);
        });
        Assertions.assertEquals(1, wallet.getEvents().size());
    }

    @Test
    @DisplayName("Edit description")
    void editDescription() {
        Wallet wallet = populate();
        wallet.editDescription("NewDescription");

        Assertions.assertInstanceOf(WalletDescriptionEditedEvent.class, wallet.getEvents().get(1));
        Assertions.assertEquals("NewDescription", wallet.getDescription().getValue());
    }

    @Test
    @DisplayName("Edit description should throw WalletValidationException")
    void editDescriptionShouldThrowWalletValidationException() {
        Wallet wallet = populate();

        Assertions.assertThrows(WalletValidationException.class, () -> {
            wallet.editDescription(null);
        });
        Assertions.assertEquals(1, wallet.getEvents().size());
    }

    @Test
    @DisplayName("Update balance")
    void updateBalance() {
        Wallet wallet = populate();
        wallet.updateBalance(BigDecimal.valueOf(2300.0));

        Assertions.assertInstanceOf(WalletBalanceUpdatedEvent.class, wallet.getEvents().get(1));
        Assertions.assertEquals(BigDecimal.valueOf(2300.0), wallet.getBalance().getValue());
    }

    @Test
    @DisplayName("Change currency code")
    void changeCurrencyCode() {
        Wallet wallet = populate();
        wallet.changeCurrencyCode(CurrencyCode.USD);

        Assertions.assertInstanceOf(WalletCurrencyCodeChangedEvent.class, wallet.getEvents().get(1));
        Assertions.assertEquals(CurrencyCode.USD, wallet.getBalance().getCurrencyCode());
    }

    @Test
    @DisplayName("Change type")
    void changeType() {
        Wallet wallet = populate();
        wallet.changeType(WalletType.DEBIT_CARD);

        Assertions.assertInstanceOf(WalletTypeChangedEvent.class, wallet.getEvents().get(1));
        Assertions.assertEquals(WalletType.DEBIT_CARD, wallet.getType());
    }

    @Test
    @DisplayName("Set main")
    void setMain() {
        Wallet wallet = populate();
        wallet.setMain(false);

        Assertions.assertInstanceOf(WalletMainSetEvent.class, wallet.getEvents().get(1));
        Assertions.assertFalse(wallet.getMain());
    }

    @Test
    @DisplayName("Delete wallet")
    void deleteWallet() {
        Wallet wallet = populate();
        wallet.delete();

        Assertions.assertInstanceOf(WalletDeletedEvent.class, wallet.getEvents().get(1));
        Assertions.assertTrue(wallet.getIsDeleted());
    }

    @Test
    @DisplayName("Delete wallet should throw WalletAlreadyDeletedException")
    void deleteWalletShouldThrowWalletAlreadyDeletedException() {
        Wallet wallet = populate();
        wallet.delete();

        Assertions.assertInstanceOf(WalletDeletedEvent.class, wallet.getEvents().get(1));
        Assertions.assertThrows(WalletAlreadyDeletedException.class, wallet::delete);
        Assertions.assertEquals(2, wallet.getEvents().size());
    }
}
