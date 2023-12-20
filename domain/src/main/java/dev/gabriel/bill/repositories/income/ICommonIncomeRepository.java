package dev.gabriel.bill.repositories.income;

import dev.gabriel.bill.entities.income.CommonIncome;
import dev.gabriel.shared.exceptions.NotFoundException;
import dev.gabriel.shared.repositories.IDomainRepository;

import java.util.List;

public interface ICommonIncomeRepository extends IDomainRepository<CommonIncome> {
    List<CommonIncome> getByUserId(String id) throws NotFoundException;
}
