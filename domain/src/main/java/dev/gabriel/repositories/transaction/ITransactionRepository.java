package dev.gabriel.repositories.transaction;

import dev.gabriel.entities.transaction.Transaction;
import dev.gabriel.exceptions.NotFoundException;
import dev.gabriel.repositories.IDomainRepository;

import java.util.List;

public interface ITransactionRepository extends IDomainRepository<Transaction> {
    List<Transaction> getByBillId(String id) throws NotFoundException;
    List<Transaction> getByWalletId(String id) throws NotFoundException;
}
