package dev.gabriel.transaction.repositories;

import dev.gabriel.bill.valueobjects.BillId;
import dev.gabriel.shared.repositories.IDomainRepository;
import dev.gabriel.transaction.models.Transaction;
import dev.gabriel.transaction.valueobjects.TransactionId;
import dev.gabriel.wallet.valueobjects.WalletId;

import java.util.List;
import java.util.Optional;

public interface ITransactionRepository extends IDomainRepository {
    Optional<Transaction> findById(TransactionId transactionId);
    List<Transaction> findByWalletId(WalletId walletId);
    List<Transaction> findByBillId(BillId billId);
    Transaction save(Transaction transaction);
    void deleteById(TransactionId transactionId);
}
