package dev.gabriel.bill.entities.expense;

import dev.gabriel.bill.entities.expense.Expense;
import dev.gabriel.bill.entities.expense.RecurringExpense;
import dev.gabriel.bill.entities.Bill;
import dev.gabriel.bill.entities.IRecurringBill;
import dev.gabriel.bill.entities.BillStatus;
import dev.gabriel.bill.events.expense.ExpenseCreatedEvent;
import dev.gabriel.bill.events.expense.ExpenseUpdatedEvent;
import dev.gabriel.shared.valueobjects.CurrencyType;
import dev.gabriel.bill.entities.expense.ExpenseCategory;
import dev.gabriel.shared.entities.AggregateRoot;
import dev.gabriel.shared.valueobjects.Identity;
import dev.gabriel.shared.valueobjects.Money;
import dev.gabriel.user.valueobjects.UserId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class RecurringExpenseTest {

    private RecurringExpense populate() {
        return RecurringExpense
                .create(UUID.randomUUID().toString(),
                        "Test", "Test",
                        Money.create(BigDecimal.valueOf(20), CurrencyType.BRL),
                        ExpenseCategory.OTHER, 4, BillStatus.UNPAID,
                        UserId.create(UUID.randomUUID().toString()), LocalDate.of(2023, 12, 8));
    }

    //Creates a recurring expense entity.
    @Test
    @DisplayName("Should create recurring expense successfully.")
    void createRecurringExpenseTestCase() {
        RecurringExpense recurringExpense = populate();

        Assertions.assertInstanceOf(AggregateRoot.class, recurringExpense);
        Assertions.assertInstanceOf(Bill.class, recurringExpense);
        Assertions.assertInstanceOf(Expense.class, recurringExpense);
        Assertions.assertInstanceOf(IRecurringBill.class, recurringExpense);
        Assertions.assertInstanceOf(ExpenseCreatedEvent.class, recurringExpense.getEvents().get(0));
    }

    //Will count how many cycles have till given LocalDate and pay it.
    //Once counting, set previous payment date to given LocalDate and next payment date to given LocalDate + days occurrence.
    //Will set recurring expense status to PAID.
    @Test
    @DisplayName("Should pay next payment of recurring expense properly.")
    void nextPaymentTestCase() {
        RecurringExpense recurringExpense = populate();

        Assertions.assertEquals(LocalDate.of(2023, 12, 8), recurringExpense.getPreviousPaymentDate());
        Assertions.assertEquals(LocalDate.of(2023, 12, 12), recurringExpense.getNextPaymentDate());

        recurringExpense.nextPayment(LocalDate.of(2023, 12, 12));

        Assertions.assertEquals(LocalDate.of(2023, 12, 8), recurringExpense.getStartDate());
        Assertions.assertEquals(LocalDate.of(2023, 12, 12), recurringExpense.getPreviousPaymentDate());
        Assertions.assertEquals(LocalDate.of(2023, 12, 16), recurringExpense.getNextPaymentDate());
        Assertions.assertEquals(1, recurringExpense.getCycles());
        Assertions.assertEquals(BillStatus.PAID, recurringExpense.getStatus());
        Assertions.assertInstanceOf(ExpenseUpdatedEvent.class, recurringExpense.getEvents().get(1));
    }

    //Given a LocalDate in parameter, the method will compare current cycles with paid cycles.
    //If current cycles different from paid cycles, set recurring expense status to UNPAID.
    //Or else, set recurring expense status to PAID.
    @Test
    @DisplayName("Should check payments of recurring expense properly.")
    void checkPaymentsTestCase() {
        RecurringExpense recurringExpense = populate();

        recurringExpense.checkNewPayments(LocalDate.of(2023, 12, 12));

        Assertions.assertEquals(BillStatus.UNPAID, recurringExpense.getStatus());
        Assertions.assertInstanceOf(ExpenseUpdatedEvent.class, recurringExpense.getEvents().get(1));
    }
}
