package dev.gabriel.bill.entities.income;

import dev.gabriel.bill.entities.income.Income;
import dev.gabriel.bill.entities.income.RecurringIncome;
import dev.gabriel.bill.entities.Bill;
import dev.gabriel.bill.entities.IRecurringBill;
import dev.gabriel.bill.entities.BillStatus;
import dev.gabriel.bill.events.income.IncomeCreatedEvent;
import dev.gabriel.bill.events.income.IncomeUpdatedEvent;
import dev.gabriel.bill.valueobjects.IncomeId;
import dev.gabriel.shared.valueobjects.CurrencyType;
import dev.gabriel.bill.entities.income.IncomeCategory;
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

public class RecurringIncomeTest {

    private RecurringIncome populate() {
        return RecurringIncome
                .create(UUID.randomUUID().toString(),
                        "Test", "Test",
                        Money.create(BigDecimal.valueOf(20), CurrencyType.BRL),
                        IncomeCategory.OTHER, 4, BillStatus.UNPAID,
                        UserId.create(UUID.randomUUID().toString()), LocalDate.of(2023, 12, 8));
    }

    //Creates a recurring income entity.
    @Test
    @DisplayName("Should create recurring income successfully.")
    void createRecurringIncomeTestCase() {
        RecurringIncome recurringIncome = populate();

        Assertions.assertInstanceOf(AggregateRoot.class, recurringIncome);
        Assertions.assertInstanceOf(Bill.class, recurringIncome);
        Assertions.assertInstanceOf(Income.class, recurringIncome);
        Assertions.assertInstanceOf(IRecurringBill.class, recurringIncome);
        Assertions.assertInstanceOf(IncomeCreatedEvent.class, recurringIncome.getEvents().get(0));
    }

    //Will count how many cycles have till given LocalDate and pay it.
    //Once counting, set previous payment date to given LocalDate and next payment date to given LocalDate + days occurrence.
    //Will set recurring income status to PAID.
    @Test
    @DisplayName("Should get payment of recurring income properly.")
    void nextPaymentTestCase() {
        RecurringIncome recurringIncome = populate();

        Assertions.assertEquals(LocalDate.of(2023, 12, 8), recurringIncome.getPreviousPaymentDate());
        Assertions.assertEquals(LocalDate.of(2023, 12, 12), recurringIncome.getNextPaymentDate());

        recurringIncome.nextPayment(LocalDate.of(2023, 12, 12));

        Assertions.assertEquals(LocalDate.of(2023, 12, 8), recurringIncome.getStartDate());
        Assertions.assertEquals(LocalDate.of(2023, 12, 12), recurringIncome.getPreviousPaymentDate());
        Assertions.assertEquals(LocalDate.of(2023, 12, 16), recurringIncome.getNextPaymentDate());
        Assertions.assertEquals(1, recurringIncome.getCycles());
        Assertions.assertEquals(BillStatus.PAID, recurringIncome.getStatus());
        Assertions.assertInstanceOf(IncomeUpdatedEvent.class, recurringIncome.getEvents().get(1));
    }

    //Given a LocalDate in parameter, the method will compare current cycles with paid cycles.
    //If current cycles different from paid cycles, set recurring income status to UNPAID.
    //Or else, set recurring income status to PAID.
    @Test
    @DisplayName("Should check payments of recurring income properly.")
    void checkPaymentsTestCase() {
        RecurringIncome recurringIncome = populate();

        recurringIncome.checkNewPayments(LocalDate.of(2023, 12, 12));

        Assertions.assertEquals(BillStatus.UNPAID, recurringIncome.getStatus());
        Assertions.assertInstanceOf(IncomeUpdatedEvent.class, recurringIncome.getEvents().get(1));
    }
}
