package dev.gabriel.transaction.models;

import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.shared.models.AggregateRoot;
import dev.gabriel.shared.valueobjects.Currency;
import dev.gabriel.shared.valueobjects.CurrencyCode;
import dev.gabriel.transaction.events.TransactionCreatedEvent;
import dev.gabriel.transaction.events.TransactionDeletedEvent;
import dev.gabriel.wallet.valueobjects.WalletId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

public class TransactionTests {
    Transaction populate() {
        return Transaction.create(
                UUID.randomUUID().toString(),
                WalletId.create(UUID.randomUUID().toString()),
                BillId.create(UUID.randomUUID().toString()),
                Currency.create(BigDecimal.valueOf(40.0), CurrencyCode.BRL)
        );
    }

    @Test
    @DisplayName("Create transaction test case: success")
    void createTransactionTestCaseSuccess() {
        Transaction transaction = populate();

        Assertions.assertInstanceOf(AggregateRoot.class, transaction);
        Assertions.assertInstanceOf(TransactionCreatedEvent.class, transaction.getEvents().get(0));
    }

    @Test
    @DisplayName("Delete transaction test case: success")
    void deleteTransactionTestCaseSuccess() {
        Transaction transaction = populate();
        transaction.delete();

        Assertions.assertInstanceOf(TransactionDeletedEvent.class, transaction.getEvents().get(1));
    }
}
