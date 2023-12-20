package dev.gabriel.repositories.bill.income;

import dev.gabriel.entities.bill.income.RecurringIncome;
import dev.gabriel.exceptions.NotFoundException;
import dev.gabriel.repositories.IDomainRepository;

import java.util.List;

public interface IRecurringIncomeRepository extends IDomainRepository<RecurringIncome> {
    List<RecurringIncome> getByUserId(String id) throws NotFoundException;
}
