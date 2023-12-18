package dev.gabriel.entities.wallet.incomes;

import dev.gabriel.enums.IncomeCategory;
import dev.gabriel.valueobjects.Money;

import java.util.Date;
import java.util.List;

public interface IIncomeList {
    void createCommonIncome(String id, String name, String comment, Money amount, IncomeCategory category);
    void createRecurringIncome(String id, String name, String comment, Money amount, IncomeCategory category, Integer daysOccurrence);
    void createRecurringIncome(String id, String name, String comment, Money amount, IncomeCategory category, Integer daysOccurrence, Date startDate);
    void removeIncome(Income income);
    List<CommonIncome> getCommonIncomes();
    List<RecurringIncome> getRecurringIncomes();
    CommonIncome getCommonIncomeById(String id);
    RecurringIncome getRecurringIncomeById(String id);
}
