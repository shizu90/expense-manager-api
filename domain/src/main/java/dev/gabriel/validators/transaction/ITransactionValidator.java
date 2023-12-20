package dev.gabriel.validators.transaction;

import dev.gabriel.entities.transaction.Transaction;
import dev.gabriel.enums.TransactionType;
import dev.gabriel.valueobjects.Identity;
import dev.gabriel.valueobjects.Money;

import java.util.List;

public interface ITransactionValidator {
    String validateBillId(Identity billId);
    String validateWalletId(Identity walletId);
    String validateTotalPaid(Money totalPaid);
    String validateTransactionType(TransactionType transactionType);
    List<String> validate(Transaction transaction);
}
