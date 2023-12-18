package dev.gabriel.validations;

import dev.gabriel.entities.wallet.expenses.CommonExpense;
import dev.gabriel.entities.wallet.expenses.RecurringExpense;
import dev.gabriel.enums.CurrencyType;
import dev.gabriel.enums.ExpenseCategory;
import dev.gabriel.valueobjects.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ExpenseValidatorTest {
    @Test
    @DisplayName("Validating a common income without constraints violations.")
    public void validateCommonExpenseTestCase1() {
        CommonExpense commonExpense = CommonExpense
                .create(1L, "Expense Name", "Expense Comment", Money.create(800.0, CurrencyType.BRL), ExpenseCategory.GIFTS);
        ExpenseValidator expenseValidator = new ExpenseValidator();

        List<String> errors = expenseValidator.validate(commonExpense);

        Assertions.assertEquals(0, errors.size());
    }

    @Test
    @DisplayName("Validating a common income with constraints violations.")
    public void validateCommonExpenseTestCase2() {
        CommonExpense commonExpense = CommonExpense
                .create(1L, "", "", Money.create(-800.0, CurrencyType.BRL), null);
        List<String> expectedErrors = Arrays.asList(new String[] {
                "Name must be between 1 and 255 characters.",
                "Category must be present.",
                "Amount must be greater than 0."
        });
        ExpenseValidator expenseValidator =  new ExpenseValidator();
        List<String> errors = expenseValidator.validate(commonExpense);

        Assertions.assertEquals(expectedErrors, errors);
    }

    @Test
    @DisplayName("Validating a recurring expense without constraints violations.")
    public void validateRecurringExpenseTestCase1() {
        RecurringExpense recurringExpense = RecurringExpense
                .create(1L, "Expense Name", "Expense Comment", Money.create(800.0, CurrencyType.BRL), ExpenseCategory.GIFTS, 4);
        ExpenseValidator expenseValidator = new ExpenseValidator();

        List<String> errors = expenseValidator.validate(recurringExpense);

        Assertions.assertEquals(0, errors.size());
    }

    @Test
    @DisplayName("Validating a recurring expense with constraints violations.")
    public void validateRecurringExpenseTestCase2() {
        RecurringExpense recurringExpense = RecurringExpense
                .create(1L, "", "", Money.create(-800.0, CurrencyType.BRL), null, 0);
        List<String> expectedErrors = Arrays.asList(new String[] {
                "Name must be between 1 and 255 characters.",
                "Category must be present.",
                "Amount must be greater than 0.",
                "Days occurrence must be greater than 0."
        });
        ExpenseValidator expenseValidator = new ExpenseValidator();
        List<String> errors = expenseValidator.validate(recurringExpense);

        Assertions.assertEquals(expectedErrors, errors);
    }
}
