package dev.gabriel.bill.validation.expense;


import dev.gabriel.bill.entities.expense.RecurringExpense;
import dev.gabriel.bill.validation.expense.ExpenseValidator;
import dev.gabriel.bill.validation.expense.IExpenseValidator;
import dev.gabriel.shared.valueobjects.CurrencyType;
import dev.gabriel.shared.valueobjects.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class ExpenseValidatorTest {
    RecurringExpense populate() {
        return RecurringExpense.create(
                UUID.randomUUID().toString(),
                "", "", Money.create(BigDecimal.valueOf(-20), CurrencyType.BRL),
                null, 0, null, null);
    }

    @Test
    @DisplayName("Should validate name properly.")
    void validateNameTestCase() {
        RecurringExpense recurringExpense = populate();
        IExpenseValidator expenseValidator = new ExpenseValidator();
        String error = expenseValidator.validateName(recurringExpense.getName());

        Assertions.assertNotNull(error);
    }

    @Test
    @DisplayName("Should validate comment properly.")
    void validateCommentTestCase() {
        RecurringExpense recurringExpense = populate();
        IExpenseValidator expenseValidator = new ExpenseValidator();
        String error = expenseValidator.validateComment(recurringExpense.getComment());

        Assertions.assertNull(error);
    }

    @Test
    @DisplayName("Should validate amount properly.")
    void validateAmountTestCase() {
        RecurringExpense recurringExpense = populate();
        IExpenseValidator expenseValidator = new ExpenseValidator();
        String error = expenseValidator.validateAmount(recurringExpense.getAmount());

        Assertions.assertNotNull(error);
    }

    @Test
    @DisplayName("Should validate category properly.")
    void validateCategoryTestCase() {
        RecurringExpense recurringExpense = populate();
        IExpenseValidator expenseValidator = new ExpenseValidator();
        String error = expenseValidator.validateCategory(recurringExpense.getCategory());

        Assertions.assertNotNull(error);
    }

    @Test
    @DisplayName("Should validate status properly.")
    void validateStatusTestCase() {
        RecurringExpense recurringExpense = populate();
        IExpenseValidator expenseValidator = new ExpenseValidator();
        String error = expenseValidator.validateStatus(recurringExpense.getStatus());

        Assertions.assertNotNull(error);
    }

    @Test
    @DisplayName("Should validate payments date properly.")
    void validatePaymentsDateTestCase() {
        RecurringExpense recurringExpense = populate();
        IExpenseValidator expenseValidator = new ExpenseValidator();
        String error = expenseValidator.validatePaymentsDate(recurringExpense.getPreviousPaymentDate(), recurringExpense.getNextPaymentDate());

        Assertions.assertNull(error);
    }

    @Test
    @DisplayName("Should validate days occurrence properly.")
    void validateDaysOccurrenceTestCase() {
        RecurringExpense recurringExpense = populate();
        IExpenseValidator expenseValidator = new ExpenseValidator();
        String error = expenseValidator.validateDaysOccurrence(recurringExpense.getDaysOccurrence());

        Assertions.assertNotNull(error);
    }

    @Test
    @DisplayName("Should validate user id properly.")
    void validateUserIdTestCase() {
        RecurringExpense recurringExpense = populate();
        IExpenseValidator expenseValidator = new ExpenseValidator();
        String error = expenseValidator.validateUserId(recurringExpense.getUserId());

        Assertions.assertNotNull(error);
    }

    @Test
    @DisplayName("Should validate cycles properly.")
    void validateCyclesTestCase() {
        RecurringExpense recurringExpense = populate();
        IExpenseValidator expenseValidator = new ExpenseValidator();
        String error = expenseValidator.validateCycles(recurringExpense.getCycles());

        Assertions.assertNull(error);
    }

    @Test
    @DisplayName("Should validate start date properly.")
    void validateStartDateTestCase() {
        RecurringExpense recurringExpense = populate();
        IExpenseValidator expenseValidator = new ExpenseValidator();
        String error = expenseValidator.validateStartDate(recurringExpense.getStartDate());

        Assertions.assertNull(error);
    }

    @Test
    @DisplayName("Should validate expense properly.")
    void validateExpenseTestCase() {
        RecurringExpense recurringExpense = populate();
        IExpenseValidator expenseValidator = new ExpenseValidator();
        List<String> errors = expenseValidator.validate(recurringExpense);

        Assertions.assertEquals(5, errors.size());
    }
}
