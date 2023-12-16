package dev.gabriel.entities;

import dev.gabriel.entities.enums.ExpenseCategory;
import dev.gabriel.entities.enums.IncomeCategory;
import dev.gabriel.primitives.AggregateRoot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class WalletTest {
    @Test
    @DisplayName("Should create wallet successfully.")
    public void createWalletTestCase() {
        Wallet wallet = Wallet.create(1L, 80.0, "Wallet");

        Assertions.assertInstanceOf(AggregateRoot.class, wallet);
        Assertions.assertEquals(1L, wallet.getIdentity());
    }

    @Test
    @DisplayName("Should add expense properly.")
    public void addExpenseTestCase() {
        Wallet wallet = Wallet.create(1L, 80.0, "Wallet");

        Assertions.assertEquals(0, wallet.getExpenses().size());

        wallet.addExpense(CommonExpense.create(1L, "Expense", "Expense", 0.0, ExpenseCategory.ENTERTAINMENT));

        Assertions.assertEquals(1, wallet.getExpenses().size());
    }

    @Test
    @DisplayName("Should remove expense properly.")
    public void removeExpenseTestCase() {
        Wallet wallet = Wallet.create(1L, 80.0, "Wallet");
        Expense commonExpense = CommonExpense
                .create(2L, "Expense", "Expense", 0.0, ExpenseCategory.ENTERTAINMENT);
        wallet.addExpense(CommonExpense.create(1L, "Expense", "Expense", 0.0, ExpenseCategory.ENTERTAINMENT));
        wallet.addExpense(commonExpense);

        Assertions.assertEquals(2, wallet.getExpenses().size());

        wallet.removeExpense(commonExpense);

        Assertions.assertEquals(1, wallet.getExpenses().size());
    }

    @Test
    @DisplayName("Should add income properly.")
    public void addIncomeTestCase() {
        Wallet wallet = Wallet.create(1L, 80.0, "Wallet");

        Assertions.assertEquals(0, wallet.getIncomes().size());

        wallet.addIncome(CommonIncome.create(1L, "Income", "Income", 0.0, IncomeCategory.WAGE));

        Assertions.assertEquals(1, wallet.getIncomes().size());
    }

    @Test
    @DisplayName("Should remove income properly.")
    public void removeIncomeTestCase() {
        Wallet wallet = Wallet.create(1L, 80.0, "Wallet");
        Income commonIncome = CommonIncome
                .create(2L, "Income", "Income", 0.0, IncomeCategory.WAGE);
        wallet.addIncome(CommonIncome.create(1L, "Income", "Income", 0.0, IncomeCategory.WAGE));
        wallet.addIncome(commonIncome);

        Assertions.assertEquals(2, wallet.getIncomes().size());

        wallet.removeIncome(commonIncome);

        Assertions.assertEquals(1, wallet.getIncomes().size());
    }

    @Test
    @DisplayName("Should get common expenses properly.")
    public void getCommonExpensesTestCase() {
        Wallet wallet = Wallet.create(1L, 80.0, "Wallet");
        CommonExpense commonExpense1 = CommonExpense.create(1L, "Expense", "Expense", 0.0, ExpenseCategory.ENTERTAINMENT);
        List<CommonExpense> commonExpenses = new ArrayList<>();
        commonExpenses.add(commonExpense1);
        commonExpenses.add(commonExpense1);
        wallet.addExpense(commonExpense1);
        wallet.addExpense(commonExpense1);
        wallet.addExpense(RecurringExpense.create(1L, "Recurring", "Recurring", 0.0, ExpenseCategory.ENTERTAINMENT, 4));

        Assertions.assertEquals(commonExpenses, wallet.getCommonExpenses());
    }

    @Test
    @DisplayName("Should get common incomes properly.")
    public void getCommonIncomesTestCase() {
        Wallet wallet = Wallet.create(1L, 80.0, "Wallet");
        CommonIncome commonIncome1 = CommonIncome.create(1L, "Income", "Income", 0.0, IncomeCategory.WAGE);
        List<CommonIncome> commonIncomes = new ArrayList<>();
        commonIncomes.add(commonIncome1);
        commonIncomes.add(commonIncome1);
        wallet.addIncome(commonIncome1);
        wallet.addIncome(commonIncome1);
        wallet.addIncome(RecurringIncome.create(1L, "Recurring", "Recurring", 0.0, IncomeCategory.WAGE, 4));

        Assertions.assertEquals(commonIncomes, wallet.getCommonIncomes());
    }

    @Test
    @DisplayName("Should get recurring expenses properly.")
    public void getRecurringExpensesTestCase() {
        Wallet wallet = Wallet.create(1L, 80.0, "Wallet");
        RecurringExpense recurringExpense1 = RecurringExpense
                .create(1L, "Recurring", "Recurring", 0.0, ExpenseCategory.ENTERTAINMENT, 4);
        List<RecurringExpense> recurringExpenses = new ArrayList<>();
        recurringExpenses.add(recurringExpense1);
        recurringExpenses.add(recurringExpense1);
        wallet.addExpense(recurringExpense1);
        wallet.addExpense(recurringExpense1);
        wallet.addExpense(CommonExpense.create(1L, "Expense", "Expense", 0.0, ExpenseCategory.ENTERTAINMENT));

        Assertions.assertEquals(recurringExpenses, wallet.getRecurringExpenses());
    }

    @Test
    @DisplayName("Should get recurring incomes properly.")
    public void getRecurringIncomesTestCase() {
        Wallet wallet = Wallet.create(1L, 80.0, "Wallet");
        RecurringIncome recurringIncome1 = RecurringIncome
                .create(1L, "Recurring", "Recurring", 0.0, IncomeCategory.WAGE, 4);
        List<RecurringIncome> recurringIncomes = new ArrayList<>();
        recurringIncomes.add(recurringIncome1);
        recurringIncomes.add(recurringIncome1);
        wallet.addIncome(recurringIncome1);
        wallet.addIncome(recurringIncome1);
        wallet.addIncome(CommonIncome.create(1L, "Income", "Income", 0.0, IncomeCategory.WAGE));

        Assertions.assertEquals(recurringIncomes, wallet.getRecurringIncomes());
    }
}
