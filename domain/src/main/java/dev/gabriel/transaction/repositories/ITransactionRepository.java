package dev.gabriel.transaction.repositories;

import dev.gabriel.transaction.entities.Transaction;
import dev.gabriel.shared.exceptions.NotFoundException;
import dev.gabriel.shared.repositories.IDomainRepository;

import java.util.List;

public interface ITransactionRepository extends IDomainRepository<Transaction> {
    List<Transaction> getByBillId(String id) throws NotFoundException;
    List<Transaction> getByWalletId(String id) throws NotFoundException;
}
