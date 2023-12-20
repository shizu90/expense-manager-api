package dev.gabriel.entities.wallet;

import dev.gabriel.enums.CurrencyType;
import dev.gabriel.primitives.AggregateRoot;
import dev.gabriel.valueobjects.Identity;
import dev.gabriel.valueobjects.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

public class WalletTest {
    @Test
    @DisplayName("Should create wallet successfully.")
    void createWalletTestCase() {
        String uid = UUID.randomUUID().toString();
        String userUid = UUID.randomUUID().toString();
        Wallet wallet = Wallet.create(uid, Money.create(BigDecimal.ZERO, CurrencyType.BRL), "Wallet", "Test", Identity.create(userUid));

        Assertions.assertInstanceOf(AggregateRoot.class, wallet);
        Assertions.assertEquals(uid, wallet.getIdentity());
        Assertions.assertEquals(userUid, wallet.getUserId().getAtomicValues().get(0));
    }
}
