package dev.gabriel.entities.wallet.expenses;

import dev.gabriel.enums.ExpenseCategory;
import dev.gabriel.valueobjects.Identity;
import dev.gabriel.valueobjects.Money;

import java.util.Date;
import java.util.List;

public interface IExpenseList {
    void createCommonExpense(String id, String name, String comment, Money amount, ExpenseCategory category);
    void createRecurringExpense(String id, String name, String comment, Money amount, ExpenseCategory category, Integer daysOccurrence);
    void createRecurringExpense(String id, String name, String comment, Money amount, ExpenseCategory category, Integer daysOccurrence, Date startDate);
    void removeExpense(Expense expense);
    List<CommonExpense> getCommonExpenses();
    List<RecurringExpense> getRecurringExpenses();
    CommonExpense getCommonExpenseById(String id);
    RecurringExpense getRecurringExpenseById(String id);
}
