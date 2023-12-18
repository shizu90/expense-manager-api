package dev.gabriel.entities.wallet;

import dev.gabriel.entities.wallet.expenses.CommonExpense;
import dev.gabriel.entities.wallet.expenses.Expense;
import dev.gabriel.entities.wallet.expenses.RecurringExpense;
import dev.gabriel.entities.wallet.incomes.CommonIncome;
import dev.gabriel.entities.wallet.incomes.Income;
import dev.gabriel.entities.wallet.incomes.RecurringIncome;
import dev.gabriel.enums.ExpenseCategory;
import dev.gabriel.enums.IncomeCategory;
import dev.gabriel.primitives.AggregateRoot;
import dev.gabriel.valueobjects.Identity;
import dev.gabriel.valueobjects.Money;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Wallet extends AggregateRoot {
    private String name;
    private String comment;
    private Money balance;
    private Identity userId;
    private WalletContent content;
    private Date createdAt;
    private Date lastBalanceUpdate;

    private Wallet(String id, String name, String comment, Money balance, Identity userId) {
        super(id);
        this.name = name;
        this.comment = comment;
        this.balance = balance;
        this.userId = userId;
        this.content = WalletContent.create(id);
        this.createdAt = new Date();
        this.lastBalanceUpdate = createdAt;
    }

    public static Wallet create(String id, Money balance, String name, String comment, Identity userId) {
        return new Wallet(id, name, comment, balance, userId);
    }

    public void createCommonExpense(String id, String name, String comment, Money amount, ExpenseCategory category) {
        content.createCommonExpense(id, name, comment, amount, category);
    }

    public void createCommonIncome(String id, String name, String comment, Money amount, IncomeCategory category) {
        content.createCommonIncome(id, name, comment, amount, category);
    }

    public void createRecurringExpense(String id, String name, String comment, Money amount, ExpenseCategory category, Integer daysOccurrence, Date startDate) {
        if(startDate == null) {
            content.createRecurringExpense(id, name, comment, amount, category, daysOccurrence);
        }else content.createRecurringExpense(id, name, comment, amount, category, daysOccurrence, startDate);
    }

    public void createRecurringIncome(String id, String name, String comment, Money amount, IncomeCategory category, Integer daysOccurrence, Date startDate) {
        if(startDate == null) {
            content.createRecurringIncome(id, name, comment, amount, category, daysOccurrence);
        }else content.createRecurringIncome(id, name, comment, amount, category, daysOccurrence, startDate);
    }

    public void removeExpense(Expense expense) {
        content.removeExpense(expense);
    }

    public void removeIncome(Income income) {
        content.removeIncome(income);
    }

    public List<CommonExpense> commonExpenses() {
        return content.getCommonExpenses();
    }

    public List<CommonIncome> commonIncomes() {
        return content.getCommonIncomes();
    }

    public List<RecurringExpense> recurringExpenses() {
        return content.getRecurringExpenses();
    }

    public List<RecurringIncome> recurringIncomes() {
        return content.getRecurringIncomes();
    }

    public CommonExpense commonExpense(String id) {
        return content.getCommonExpenseById(id);
    }

    public CommonIncome commonIncome(String id) {
        return content.getCommonIncomeById(id);
    }

    public RecurringExpense recurringExpense(String id) {
        return content.getRecurringExpenseById(id);
    }

    public RecurringIncome recurringIncome(String id) {
        return content.getRecurringIncomeById(id);
    }
}
