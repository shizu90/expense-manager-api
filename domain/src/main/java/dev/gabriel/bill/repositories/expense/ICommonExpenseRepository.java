package dev.gabriel.bill.repositories.expense;

import dev.gabriel.bill.entities.expense.CommonExpense;
import dev.gabriel.shared.exceptions.NotFoundException;
import dev.gabriel.shared.repositories.IDomainRepository;

import java.util.List;

public interface ICommonExpenseRepository extends IDomainRepository<CommonExpense> {
    List<CommonExpense> getByUserId(String id) throws NotFoundException;
}
