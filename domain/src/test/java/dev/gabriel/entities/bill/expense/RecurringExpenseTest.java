package dev.gabriel.entities.bill.expense;

import dev.gabriel.entities.bill.Bill;
import dev.gabriel.entities.bill.IRecurringBill;
import dev.gabriel.enums.BillStatus;
import dev.gabriel.enums.CurrencyType;
import dev.gabriel.enums.ExpenseCategory;
import dev.gabriel.primitives.AggregateRoot;
import dev.gabriel.valueobjects.Identity;
import dev.gabriel.valueobjects.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class RecurringExpenseTest {

    //Creates a recurring expense entity.
    @Test
    @DisplayName("Should create recurring expense successfully.")
    void createRecurringExpenseTestCase() {
        RecurringExpense recurringExpense = RecurringExpense
                .create(UUID.randomUUID().toString(),
                        "Test", "Test",
                        Money.create(BigDecimal.valueOf(20), CurrencyType.BRL),
                        ExpenseCategory.OTHER, 4, BillStatus.UNPAID, Identity.create(UUID.randomUUID().toString()));

        Assertions.assertInstanceOf(AggregateRoot.class, recurringExpense);
        Assertions.assertInstanceOf(Bill.class, recurringExpense);
        Assertions.assertInstanceOf(Expense.class, recurringExpense);
        Assertions.assertInstanceOf(IRecurringBill.class, recurringExpense);
    }

    //Will count how many cycles have till given LocalDate and pay it.
    //Once counting, set previous payment date to given LocalDate and next payment date to given LocalDate + days occurrence.
    //Will set recurring expense status to PAID.
    @Test
    @DisplayName("Should pay next payment of recurring expense properly.")
    void nextPaymentTestCase() {
        RecurringExpense recurringExpense = RecurringExpense
                .create(UUID.randomUUID().toString(),
                        "Test", "Test",
                        Money.create(BigDecimal.valueOf(20), CurrencyType.BRL),
                        ExpenseCategory.OTHER, 4, BillStatus.UNPAID,
                        Identity.create(UUID.randomUUID().toString()), LocalDate.of(2023, 12, 8));

        Assertions.assertEquals(LocalDate.of(2023, 12, 8), recurringExpense.getPreviousPaymentDate().getAtomicValues().get(0));
        Assertions.assertEquals(LocalDate.of(2023, 12, 12), recurringExpense.getNextPaymentDate().getAtomicValues().get(0));

        recurringExpense.nextPayment(LocalDate.of(2023, 12, 12));

        Assertions.assertEquals(LocalDate.of(2023, 12, 8), recurringExpense.getStartDate().getAtomicValues().get(0));
        Assertions.assertEquals(LocalDate.of(2023, 12, 12), recurringExpense.getPreviousPaymentDate().getAtomicValues().get(0));
        Assertions.assertEquals(LocalDate.of(2023, 12, 16), recurringExpense.getNextPaymentDate().getAtomicValues().get(0));
        Assertions.assertEquals(1, recurringExpense.getCycles());
        Assertions.assertEquals(BillStatus.PAID, recurringExpense.getStatus());
    }

    //Given a LocalDate in parameter, the method will compare current cycles with paid cycles.
    //If current cycles different from paid cycles, set recurring expense status to UNPAID.
    //Or else, set recurring expense status to PAID.
    @Test
    @DisplayName("Should check payments of recurring expense properly.")
    void checkPaymentsTestCase() {
        RecurringExpense recurringExpense = RecurringExpense
                .create(UUID.randomUUID().toString(),
                        "Test", "Test",
                        Money.create(BigDecimal.valueOf(20), CurrencyType.BRL),
                        ExpenseCategory.OTHER, 4, BillStatus.PAID,
                        Identity.create(UUID.randomUUID().toString()), LocalDate.of(2023, 12, 8));

        recurringExpense.checkPayments(LocalDate.of(2023, 12, 12));

        Assertions.assertEquals(BillStatus.UNPAID, recurringExpense.getStatus());
    }
}
