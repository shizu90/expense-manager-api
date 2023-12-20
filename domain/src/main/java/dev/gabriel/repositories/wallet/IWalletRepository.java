package dev.gabriel.repositories.wallet;

import dev.gabriel.entities.bill.expense.RecurringExpense;
import dev.gabriel.entities.bill.income.RecurringIncome;
import dev.gabriel.entities.wallet.Wallet;
import dev.gabriel.exceptions.DifferentCurrencyTypeException;
import dev.gabriel.exceptions.InsufficientFundsException;
import dev.gabriel.exceptions.NotFoundException;
import dev.gabriel.repositories.IDomainRepository;

import java.util.List;

public interface IWalletRepository extends IDomainRepository<Wallet> {
    List<Wallet> getByUserId(String id) throws NotFoundException;
    void payCycles(RecurringExpense recurringExpense) throws DifferentCurrencyTypeException, InsufficientFundsException;
    void receiveCycles(RecurringIncome recurringIncome) throws DifferentCurrencyTypeException;
}
