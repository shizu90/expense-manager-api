package dev.gabriel.transaction.validation;

import dev.gabriel.transaction.entities.Transaction;
import dev.gabriel.transaction.entities.TransactionType;
import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.shared.valueobjects.Money;

import java.util.List;

public interface ITransactionValidator {
    String validateBillId(Identity billId);
    String validateWalletId(Identity walletId);
    String validateTotalPaid(Money totalPaid);
    String validateTransactionType(TransactionType transactionType);
    List<String> validate(Transaction transaction);
}
