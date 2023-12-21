package dev.gabriel.transaction.entities;

import dev.gabriel.bill.valueobjects.ExpenseId;
import dev.gabriel.shared.entities.AggregateRoot;
import dev.gabriel.shared.valueobjects.CurrencyType;
import dev.gabriel.shared.valueobjects.Money;
import dev.gabriel.transaction.events.TransactionCreatedEvent;
import dev.gabriel.wallet.valueobjects.WalletId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

public class TransactionTest {

    private Transaction populate() {
        return Transaction.create(UUID.randomUUID().toString(),
                WalletId.create(UUID.randomUUID().toString()),
                ExpenseId.create(UUID.randomUUID().toString()),
                Money.create(BigDecimal.valueOf(500.0), CurrencyType.EUR),
                TransactionType.IN);
    }

    @Test
    @DisplayName("Should create transaction successfully.")
    void createTransactionTestCase() {
        Transaction transaction = populate();

        Assertions.assertInstanceOf(AggregateRoot.class, transaction);
        Assertions.assertInstanceOf(TransactionCreatedEvent.class, transaction.getEvents().get(0));
    }
}
