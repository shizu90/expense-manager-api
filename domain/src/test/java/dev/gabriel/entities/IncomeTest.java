package dev.gabriel.entities;

import dev.gabriel.entities.enums.IncomeCategory;
import dev.gabriel.primitives.Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IncomeTest {
    @Test
    @DisplayName("Should create common income entity successfully.")
    public void createCommonIncomeTestCase() {
        CommonIncome commonIncome = CommonIncome.create(1L, "IncomeTest", "IncomeTest", 800.0, IncomeCategory.INVESTMENTS);

        Assertions.assertInstanceOf(Entity.class, commonIncome);
        Assertions.assertInstanceOf(Income.class, commonIncome);
        Assertions.assertEquals(1L, commonIncome.getIdentity());
    }

    @Test
    @DisplayName("Should create recurring income entity successfully.")
    public void createRecurringIncomeTestCase() {
        RecurringIncome recurringIncome = RecurringIncome
                .create(1L, "ExpenseTest", "ExpenseTest", 800.0, IncomeCategory.INVESTMENTS, 4);

        Assertions.assertInstanceOf(Entity.class, recurringIncome);
        Assertions.assertInstanceOf(IRecurringBill.class, recurringIncome);
        Assertions.assertInstanceOf(Income.class, recurringIncome);
        Assertions.assertEquals(1L, recurringIncome.getIdentity());
        Assertions.assertEquals(recurringIncome.getCreatedAt(), recurringIncome.getNextPaymentDate());
        Assertions.assertEquals(recurringIncome.getCreatedAt(), recurringIncome.getPreviousPaymentDate());
    }
}
