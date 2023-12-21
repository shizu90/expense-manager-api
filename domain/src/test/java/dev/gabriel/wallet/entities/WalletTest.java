package dev.gabriel.wallet.entities;

import dev.gabriel.shared.valueobjects.CurrencyType;
import dev.gabriel.shared.entities.AggregateRoot;
import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.shared.valueobjects.Money;
import dev.gabriel.user.valueobjects.UserId;
import dev.gabriel.wallet.entities.Wallet;
import dev.gabriel.wallet.events.WalletCreatedEvent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

public class WalletTest {

    public Wallet populate() {
        return Wallet
                .create(
                        UUID.randomUUID().toString(),
                        Money.create(BigDecimal.ZERO, CurrencyType.BRL),
                        "Wallet", "Test", UserId.create(UUID.randomUUID().toString()));
    }

    @Test
    @DisplayName("Should create wallet successfully.")
    void createWalletTestCase() {
        Wallet wallet = populate();

        Assertions.assertInstanceOf(AggregateRoot.class, wallet);
        Assertions.assertInstanceOf(WalletCreatedEvent.class, wallet.getEvents().get(0));
    }
}
