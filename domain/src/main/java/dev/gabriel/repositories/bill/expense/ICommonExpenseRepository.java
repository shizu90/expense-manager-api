package dev.gabriel.repositories.bill.expense;

import dev.gabriel.entities.bill.expense.CommonExpense;
import dev.gabriel.exceptions.NotFoundException;
import dev.gabriel.repositories.IDomainRepository;

import java.util.List;

public interface ICommonExpenseRepository extends IDomainRepository<CommonExpense> {
    List<CommonExpense> getByUserId(String id) throws NotFoundException;
}
