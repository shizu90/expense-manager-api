package dev.gabriel.bill.entities.expense;

import dev.gabriel.bill.entities.Bill;
import dev.gabriel.bill.entities.BillStatus;
import dev.gabriel.shared.valueobjects.CurrencyType;
import dev.gabriel.shared.entities.AggregateRoot;
import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.shared.valueobjects.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

public class CommonExpenseTest {
    @Test
    @DisplayName("Should create common expense successfully.")
    void createCommonExpenseTestCase() {
        CommonExpense commonExpense = CommonExpense
                .create(UUID.randomUUID().toString(),
                        "Test", "Test",
                        Money.create(BigDecimal.valueOf(20), CurrencyType.BRL), ExpenseCategory.OTHER,
                        BillStatus.UNPAID, Identity.create(UUID.randomUUID().toString()));

        Assertions.assertInstanceOf(AggregateRoot.class, commonExpense);
        Assertions.assertInstanceOf(Bill.class, commonExpense);
        Assertions.assertInstanceOf(Expense.class, commonExpense);
    }
}
