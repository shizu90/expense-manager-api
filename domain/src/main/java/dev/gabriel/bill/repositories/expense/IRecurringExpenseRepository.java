package dev.gabriel.bill.repositories.expense;

import dev.gabriel.bill.entities.expense.RecurringExpense;
import dev.gabriel.shared.exceptions.NotFoundException;
import dev.gabriel.shared.repositories.IDomainRepository;

import java.util.List;

public interface IRecurringExpenseRepository extends IDomainRepository<RecurringExpense> {
    List<RecurringExpense> getByUserId(String id) throws NotFoundException;
}
