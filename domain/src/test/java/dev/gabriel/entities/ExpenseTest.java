package dev.gabriel.entities;

import dev.gabriel.entities.enums.ExpenseCategory;
import dev.gabriel.primitives.Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExpenseTest {
    @Test
    @DisplayName("Should create common expense successfully.")
    public void createCommonExpenseTestCase() {
        CommonExpense commonExpense = CommonExpense
                .create(1L, "ExpenseTest", "ExpenseTest", 20.0, ExpenseCategory.ENTERTAINMENT);

        Assertions.assertInstanceOf(Entity.class, commonExpense);
        Assertions.assertInstanceOf(Expense.class, commonExpense);
        Assertions.assertEquals(1L, commonExpense.getIdentity());
    }

    @Test
    @DisplayName("Should create recurring expense successfully.")
    public void createRecurringExpenseTestCase() {
        RecurringExpense recurringExpense = RecurringExpense
                .create(1L, "ExpenseTest", "ExpenseTest", 20.0, ExpenseCategory.ENTERTAINMENT, 4);

        Assertions.assertInstanceOf(Entity.class, recurringExpense);
        Assertions.assertInstanceOf(IRecurringBill.class, recurringExpense);
        Assertions.assertInstanceOf(Expense.class, recurringExpense);
        Assertions.assertEquals(1L, recurringExpense.getIdentity());
        Assertions.assertEquals(recurringExpense.getCreatedAt(), recurringExpense.getNextPaymentDate());
        Assertions.assertEquals(recurringExpense.getCreatedAt(), recurringExpense.getPreviousPaymentDate());
    }
}
