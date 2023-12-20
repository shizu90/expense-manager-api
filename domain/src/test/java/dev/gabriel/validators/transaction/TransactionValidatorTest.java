package dev.gabriel.validators.transaction;

import dev.gabriel.entities.transaction.Transaction;
import dev.gabriel.enums.CurrencyType;
import dev.gabriel.valueobjects.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class TransactionValidatorTest {
    Transaction populate() {
        return Transaction.create(UUID.randomUUID().toString(), null, null, Money.create(BigDecimal.valueOf(-20), CurrencyType.BRL), null);
    }

    @Test
    @DisplayName("Should validate total paid properly.")
    void validateTotalPaidTestCase() {
        Transaction transaction = populate();
        ITransactionValidator transactionValidator = new TransactionValidator();
        String error = transactionValidator.validateTotalPaid(transaction.getTotalPaid());

        Assertions.assertNotNull(error);
    }

    @Test
    @DisplayName("Should validate transaction type properly.")
    void validateTransactionTypeTestCase() {
        Transaction transaction = populate();
        ITransactionValidator transactionValidator = new TransactionValidator();
        String error = transactionValidator.validateTransactionType(transaction.getTransactionType());

        Assertions.assertNotNull(error);
    }

    @Test
    @DisplayName("Should validate bill id properly.")
    void validateBillIdTestCase() {
        Transaction transaction = populate();
        ITransactionValidator transactionValidator = new TransactionValidator();
        String error = transactionValidator.validateBillId(transaction.getBillId());

        Assertions.assertNotNull(error);
    }

    @Test
    @DisplayName("Should validate wallet id properly.")
    void validateWalletIdTestCase() {
        Transaction transaction = populate();
        ITransactionValidator transactionValidator = new TransactionValidator();
        String error = transactionValidator.validateWalletId(transaction.getWalletId());

        Assertions.assertNotNull(error);
    }

    @Test
    @DisplayName("Should validate transaction properly.")
    void validateTransactionTestCase() {
        Transaction transaction = populate();
        ITransactionValidator transactionValidator = new TransactionValidator();
        List<String> errors = transactionValidator.validate(transaction);

        Assertions.assertEquals(4, errors.size());
    }
}
