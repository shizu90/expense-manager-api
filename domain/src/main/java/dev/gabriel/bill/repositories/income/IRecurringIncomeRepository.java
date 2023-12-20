package dev.gabriel.bill.repositories.income;

import dev.gabriel.bill.entities.income.RecurringIncome;
import dev.gabriel.shared.exceptions.NotFoundException;
import dev.gabriel.shared.repositories.IDomainRepository;

import java.util.List;

public interface IRecurringIncomeRepository extends IDomainRepository<RecurringIncome> {
    List<RecurringIncome> getByUserId(String id) throws NotFoundException;
}
