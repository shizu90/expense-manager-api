package dev.gabriel.entities;

import dev.gabriel.entities.enums.CurrencyType;
import dev.gabriel.exceptions.MaxReachedException;
import dev.gabriel.primitives.AggregateRoot;
import dev.gabriel.valueobjects.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserTest {
    @Test
    @DisplayName("Should create user successfully.")
    public void createUserTestCase() {
        User user = User.create(1L, "User", "user@gmail.com", "user123");

        Assertions.assertInstanceOf(AggregateRoot.class, user);
        Assertions.assertEquals(1L, user.getIdentity());
    }

    @Test
    @DisplayName("Should add wallet properly.")
    public void addWalletTestCase() {
        User user = User.create(1L, "User", "user@gmail.com", "user123");

        Assertions.assertEquals(0, user.getWallets().size());

        user.addWallet(Wallet.create(1L, Money.create(800.0, CurrencyType.BRL), "Wallet1"));

        Assertions.assertEquals(1, user.getWallets().size());
    }

    @Test
    @DisplayName("Should throw MaxReachedException if wallets size reached max value in addWallet method.")
    public void addWalletMaxReachedTestCase() {
        User user = User.create(1L, "User", "user@gmail.com", "user123");
        user.addWallet(Wallet.create(1L, Money.create(800.0, CurrencyType.BRL), "Wallet1"));
        user.addWallet(Wallet.create(2L, Money.create(800.0, CurrencyType.BRL), "Wallet2"));
        user.addWallet(Wallet.create(3L, Money.create(800.0, CurrencyType.BRL), "Wallet3"));
        user.addWallet(Wallet.create(4L, Money.create(800.0, CurrencyType.BRL), "Wallet4"));

        Assertions.assertThrows(MaxReachedException.class, () -> {
            user.addWallet(Wallet.create(5L, Money.create(800.0, CurrencyType.BRL), "Wallet5"));
        });

        Assertions.assertEquals(4, user.getWallets().size());
    }

    @Test
    @DisplayName("Should remove wallet properly.")
    public void removeWalletTestCase() {
        User user = User.create(1L, "User", "user@gmail.com", "user123");
        Wallet wallet = Wallet.create(2L, Money.create(800.0, CurrencyType.BRL), "Wallet2");
        user.addWallet(Wallet.create(1L, Money.create(800.0, CurrencyType.BRL), "Wallet1"));
        user.addWallet(wallet);

        Assertions.assertEquals(2, user.getWallets().size());

        user.removeWallet(wallet);

        Assertions.assertEquals(1, user.getWallets().size());
    }
}
