package dev.gabriel.validators.transaction;

import dev.gabriel.entities.transaction.Transaction;
import dev.gabriel.valueobjects.Money;

import java.util.List;

public interface ITransactionValidator {
    String validateTotalPaid(Money totalPaid);
    List<String> validate(Transaction transaction);
}
