package dev.gabriel.repositories.bill.expense;

import dev.gabriel.entities.bill.expense.RecurringExpense;
import dev.gabriel.exceptions.NotFoundException;
import dev.gabriel.repositories.IDomainRepository;

import java.util.List;

public interface IRecurringExpenseRepository extends IDomainRepository<RecurringExpense> {
    List<RecurringExpense> getByUserId(String id) throws NotFoundException;
}
