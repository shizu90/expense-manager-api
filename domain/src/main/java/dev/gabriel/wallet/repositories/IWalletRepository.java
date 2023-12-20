package dev.gabriel.wallet.repositories;

import dev.gabriel.bill.entities.expense.RecurringExpense;
import dev.gabriel.bill.entities.income.RecurringIncome;
import dev.gabriel.wallet.entities.Wallet;
import dev.gabriel.shared.exceptions.DifferentCurrencyTypeException;
import dev.gabriel.shared.exceptions.InsufficientFundsException;
import dev.gabriel.shared.exceptions.NotFoundException;
import dev.gabriel.shared.repositories.IDomainRepository;

import java.util.List;

public interface IWalletRepository extends IDomainRepository<Wallet> {
    List<Wallet> getByUserId(String id) throws NotFoundException;
    void payCycles(RecurringExpense recurringExpense) throws DifferentCurrencyTypeException, InsufficientFundsException;
    void receiveCycles(RecurringIncome recurringIncome) throws DifferentCurrencyTypeException;
}
