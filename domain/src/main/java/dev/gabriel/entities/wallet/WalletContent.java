package dev.gabriel.entities.wallet;

import dev.gabriel.enums.ExpenseCategory;
import dev.gabriel.enums.IncomeCategory;
import dev.gabriel.entities.wallet.expenses.*;
import dev.gabriel.entities.wallet.incomes.*;
import dev.gabriel.primitives.Entity;
import dev.gabriel.valueobjects.Money;

import java.util.Date;
import java.util.List;

public class WalletContent extends Entity {
    private final IExpenseList expenses;
    private final IIncomeList incomes;

    private WalletContent(String id) {
        super(id);
        expenses = ExpenseList.create(id);
        incomes = IncomeList.create(id);
    }

    protected static WalletContent create(String id) {
        return new WalletContent(id);
    }

    protected void createCommonExpense(String id, String name, String comment, Money amount, ExpenseCategory category) {
        expenses.createCommonExpense(id, name, comment, amount, category);
    }

    protected void createRecurringExpense(String id, String name, String comment, Money amount, ExpenseCategory category, Integer daysOccurrence) {
        expenses.createRecurringExpense(id, name, comment, amount, category, daysOccurrence);
    }

    protected void createRecurringExpense(String id, String name, String comment, Money amount, ExpenseCategory category, Integer daysOccurrence, Date startDate) {
        expenses.createRecurringExpense(id, name, comment, amount, category, daysOccurrence, startDate);
    }

    protected void removeExpense(Expense expense) {
        expenses.removeExpense(expense);
    }

    protected  List<CommonExpense> getCommonExpenses() {
        return expenses.getCommonExpenses();
    }

    protected List<RecurringExpense> getRecurringExpenses() {
        return expenses.getRecurringExpenses();
    }

    protected CommonExpense getCommonExpenseById(String commonExpenseId) {
        return expenses.getCommonExpenseById(commonExpenseId);
    }

    protected RecurringExpense getRecurringExpenseById(String recurringExpenseId) {
        return expenses.getRecurringExpenseById(recurringExpenseId);
    }

    protected void createCommonIncome(String id, String name, String comment, Money amount, IncomeCategory category) {
        incomes.createCommonIncome(id, name, comment, amount, category);
    }

    protected void createRecurringIncome(String id, String name, String comment, Money amount, IncomeCategory category, Integer daysOccurrence) {
        incomes.createRecurringIncome(id, name, comment, amount, category, daysOccurrence);
    }

    protected void createRecurringIncome(String id, String name, String comment, Money amount, IncomeCategory category, Integer daysOccurrence, Date startDate) {
        incomes.createRecurringIncome(id, name, comment, amount, category, daysOccurrence, startDate);
    }

    protected void removeIncome(Income income) {
        incomes.removeIncome(income);
    }

    protected  List<CommonIncome> getCommonIncomes() {
        return incomes.getCommonIncomes();
    }

    protected List<RecurringIncome> getRecurringIncomes() {
        return incomes.getRecurringIncomes();
    }

    protected CommonIncome getCommonIncomeById(String commonIncomeId) {
        return incomes.getCommonIncomeById(commonIncomeId);
    }

    protected RecurringIncome getRecurringIncomeById(String recurringIncomeId) {
        return incomes.getRecurringIncomeById(recurringIncomeId);
    }
}
