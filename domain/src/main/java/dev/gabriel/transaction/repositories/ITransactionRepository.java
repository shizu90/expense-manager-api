package dev.gabriel.transaction.repositories;

import dev.gabriel.shared.repositories.IDomainRepository;
import dev.gabriel.transaction.models.Transaction;

import java.util.List;
import java.util.Optional;

public interface ITransactionRepository extends IDomainRepository {
    Optional<Transaction> findById(String id);
    List<Transaction> findByWalletId(String walletId);
    List<Transaction> findByBillId(String billId);
    Transaction save(Transaction transaction);
    void deleteById(String id);
}
