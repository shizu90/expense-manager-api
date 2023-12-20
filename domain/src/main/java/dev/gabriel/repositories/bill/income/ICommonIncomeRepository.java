package dev.gabriel.repositories.bill.income;

import dev.gabriel.entities.bill.income.CommonIncome;
import dev.gabriel.exceptions.NotFoundException;
import dev.gabriel.repositories.IDomainRepository;

import java.util.List;

public interface ICommonIncomeRepository extends IDomainRepository<CommonIncome> {
    List<CommonIncome> getByUserId(String id) throws NotFoundException;
}
